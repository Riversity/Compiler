package codegen;

import codegen.node.AsmNode;
import codegen.node.ins.CustomIns;
import codegen.node.ins.LIns;
import codegen.node.ins.SIns;
import ir.info.*;
import ir.node.IRType;
import sema.util.error.InternalError;

import java.util.ArrayList;
import java.util.HashMap;

import static ir.util.IRNative.irBoolType;
import static sema.util.Position.blankPos;

public class MemManager {
  public HashMap<String, Integer> offset;
  public Integer cur;

  public MemManager() {
    offset = new HashMap<>();
    cur = 0;
  }

  public String ch(IRType type) {
    return type.equals(irBoolType) ? "b" : "w";
  }

  public int add(IRBaseInfo info) {
    if(offset.containsKey(info.name)) {
      return offset.get(info.name);
      // throw new InternalError(info.name + " should not be in map", blankPos);
    }
    cur -= 4;
    offset.put(info.name, cur);
    return cur;
  }

  public AsmNode store(IRBaseInfo info, String src) {
    if(!offset.containsKey(info.name)) {
      throw new InternalError(info.name + " should be just mapped", blankPos);
    }
    int pos = offset.get(info.name);
    return new SIns("s" + ch(info.type), "sp", src, pos);
  }

  public AsmNode addStore(IRBaseInfo info, String src) {
    add(info);
    return store(info, src);
  }

  public AsmNode extract(IRBaseInfo info, String dest) {
    if(info instanceof IRFuncInfo) throw new InternalError("Extract func info error!", blankPos);
    var ret = new ArrayList<AsmNode>();
    int pos;
    if(info instanceof IRVarInfo) {
      if(!offset.containsKey(info.name)) {
        /* cur -= 4;
        offset.put(info.name, cur);
        pos = cur; */
        throw new InternalError("Should have found offset of " + info.name, blankPos);
      }
      else pos = offset.get(info.name);
      return new LIns("l" + ch(info.type), dest, "sp", pos);
    }
    if(info instanceof IRConstInfo) {
      return new CustomIns("li " + dest + ", " + info.name);
    }
    return null;
  }
}
