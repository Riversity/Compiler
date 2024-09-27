package ir.node.ins;

import ir.IRVisitor;
import ir.node.stmt.IRBlock;
import sema.util.error.MyError;

public class IRJump extends IRBaseInst {
  public IRBlock end;

  public IRJump() {}

  public String label() {
    return end.label;
  }

  public IRJump(IRBlock end) {
    this.end = end;
  }

  @Override
  public <T> T accept(IRVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
