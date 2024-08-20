package ir.node.def;

import ir.IRVisitor;
import ir.node.*;
import sema.util.error.MyError;

import java.util.ArrayList;

public class IRClassDef extends IRNode {
  public ArrayList<IRType> vars;
  public ArrayList<IRFuncDef> funcs;

  /*public IRClassDef(ArrayList<IRType> vars, ArrayList<IRFuncDef> funcs) {
    this.vars = vars;
    this.funcs = funcs;
  }*/

  @Override
  public <T> T accept(IRVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
