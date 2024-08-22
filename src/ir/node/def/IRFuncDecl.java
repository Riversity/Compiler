package ir.node.def;

import ir.IRVisitor;
import ir.node.IRNode;
import ir.node.IRType;
import sema.util.error.MyError;

import java.util.ArrayList;

public class IRFuncDecl extends IRNode {
  public String name;
  public IRType returnType;
  public ArrayList<IRType> params;

  public IRFuncDecl(String name, IRType returnType, ArrayList<IRType> params) {
    this.name = name;
    this.returnType = returnType;
    this.params = params;
  }

  @Override
  public <T> T accept(IRVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
