package ir.node.stmt;

import ir.IRVisitor;
import ir.info.*;
import ir.node.IRNode;
import sema.util.error.MyError;

public class IRExpr extends IRNode {
  public IRBaseInfo dest;
  public IRBaseInfo destAddr;

  @Override
  public <T> T accept(IRVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
