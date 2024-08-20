package ir.node.def;

import ir.IRVisitor;
import ir.node.IRNode;
import sema.util.error.MyError;

public class IRStrDef extends IRNode {
  @Override
  public <T> T accept(IRVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
