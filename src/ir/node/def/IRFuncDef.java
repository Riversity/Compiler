package ir.node.def;

import ir.IRVisitor;
import ir.info.IRVarInfo;
import ir.node.*;
import ir.node.ins.IRBaseInst;
import ir.node.stmt.*;
import sema.util.error.MyError;

import java.util.ArrayList;

public class IRFuncDef extends IRNode {
  public String name;
  public IRType returnType;
  public ArrayList<IRVarInfo> params;
  public ArrayList<IRBlock> blocks;
  public int offset;

  @Override
  public <T> T accept(IRVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
  /*
  public void addInst(IRBaseInst ins) {
    if(blocks == null) {
      blocks = new ArrayList<>();
      blocks.add(new IRBlock());
      blocks.get(0).addInst(ins);
      return;
    }
    if(blocks.isEmpty()) {
      blocks.add(new IRBlock());
      blocks.get(0).addInst(ins);
      return;
    }
    blocks.get(blocks.size() - 1).addInst(ins);
  }
  */
}
