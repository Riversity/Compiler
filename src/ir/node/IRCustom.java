package ir.node;

import ir.IRVisitor;
import sema.util.error.MyError;

public class IRCustom extends IRNode {
  public String inst;

  public <T> T accept(IRVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
