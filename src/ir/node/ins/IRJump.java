package ir.node.ins;

import ir.IRVisitor;
import ir.node.stmt.IRBlock;
import sema.util.error.MyError;

public class IRJump extends IRBaseInst {
  public String label;

  public IRBlock start;
  public IRBlock end;

  public IRJump() {}

  public IRJump(IRBlock start, IRBlock end) {
    this.start = start;
    this.end = end;
    this.label = end.label;
  }

  @Override
  public <T> T accept(IRVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
