package ir.node.stmt;

import ir.IRVisitor;
import ir.node.ins.IRBaseInst;
import sema.util.error.MyError;

public class IRBlock extends IRStmt {
  public String label;
  public IRBaseInst exit;
  // exit inst?
  public <T> T accept(IRVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
