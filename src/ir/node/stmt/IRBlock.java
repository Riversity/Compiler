package ir.node.stmt;

import ir.IRVisitor;
import sema.util.error.MyError;

public class IRBlock extends IRStmt {
  // exit inst?
  public <T> T accept(IRVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
