package ir.node;

import ir.IRVisitor;
import sema.util.error.MyError;

public abstract class IRNode {
  public <T> T accept(IRVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
