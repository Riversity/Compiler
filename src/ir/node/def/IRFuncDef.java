package ir.node.def;

import ir.IRVisitor;
import ir.info.IRVarInfo;
import ir.node.*;
import ir.node.stmt.*;
import sema.util.error.MyError;

import java.util.ArrayList;

public class IRFuncDef extends IRNode {
  public String name;
  public IRType returnType;
  public ArrayList<IRVarInfo> params;
  public ArrayList<IRBlock> blocks;

  @Override
  public <T> T accept(IRVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
