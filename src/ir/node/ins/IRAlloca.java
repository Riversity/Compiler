package ir.node.ins;

import ir.IRVisitor;
import ir.info.IRVarInfo;
import ir.node.IRType;
import sema.util.error.MyError;

public class IRAlloca extends IRBaseInst {
  public IRVarInfo dest;
  public IRType type;

  @Override
  public <T> T accept(IRVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
