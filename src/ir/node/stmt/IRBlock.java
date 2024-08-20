package ir.node.stmt;

import ir.IRVisitor;
import ir.node.IRNode;
import sema.util.error.MyError;

public class IRBlock extends IRNode {
  // insts and exit inst
  public <T> T accept(IRVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
