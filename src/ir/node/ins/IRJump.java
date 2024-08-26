package ir.node.ins;

import ir.IRVisitor;
import sema.util.error.MyError;

public class IRJump extends IRBaseInst {
  public String label;

  @Override
  public <T> T accept(IRVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
