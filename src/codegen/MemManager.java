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
  // public Integer cur;

  public MemManager() {
    offset = new HashMap<>();
  }

  public String ch(IRType type) {
    return type.equals(irBoolType) ? "b" : "w";
  }

  public void add(IRBaseInfo info, int pos) {
    if(offset.containsKey(info.name)) {
      throw new InternalError(info.name + " expected to be new", blankPos);
    }
    offset.put(info.name, pos);
  }

  public ArrayList<AsmNode> store(IRBaseInfo info, String src) {
    var ret = new ArrayList<AsmNode>();
    if(info.name.startsWith("@")) {
      ret.add(new CustomIns("la t3, " + info.name.substring(1)));
      ret.add(new SIns("sw", "t3", src, 0));
      return ret;
    }
    if(!offset.containsKey(info.name)) {
      throw new InternalError(info.name + " should have been mapped", blankPos);
    }
    int pos = offset.get(info.name);
    ret.add(new SIns("sw", "sp", src, pos));
    return ret;
  }

  /*
  public ArrayList<AsmNode> addStore(IRBaseInfo info, String src) {
    var ret = new ArrayList<AsmNode>();
    if(info.name.startsWith("@")) {
      ret.add(new CustomIns("la t3, " + info.name.substring(1)));
      ret.add(new SIns("sw", "t3", src, 0));
      // Sus
      return ret;
    }
    add(info);
    ret.addAll(store(info, src));
    return ret;
  }
  */

  public AsmNode extract(IRBaseInfo info, String dest) {
    if(info instanceof IRFuncInfo) throw new InternalError("Extract func info error!", blankPos);
    int pos;
    if(info instanceof IRVarInfo) {
      if(info.name.startsWith("@")) { // global
        return new CustomIns("la " + dest + ", " + info.name.substring(1));
      }
      if(!offset.containsKey(info.name)) {
        /* cur -= 4;
        offset.put(info.name, cur);
        pos = cur; */
        throw new InternalError("Should have found offset of " + info.name, blankPos);
      }
      else pos = offset.get(info.name);
      return new LIns("lw", dest, "sp", pos);
    }
    if(info instanceof IRConstInfo) {
      return new CustomIns("li " + dest + ", " + (info.name.equals("null") ? "0" : info.name));
    }
    throw new InternalError("Strange info of " + info.name, blankPos);
  }
}
