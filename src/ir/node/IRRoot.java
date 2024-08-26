package ir.node;

import ir.IRVisitor;
import ir.node.def.*;
import sema.util.error.MyError;

import java.util.ArrayList;

public class IRRoot extends IRNode {
  public ArrayList<IRGlobDef> defs;
  public ArrayList<IRFuncDef> funcs;

  @Override
  public <T> T accept(IRVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
