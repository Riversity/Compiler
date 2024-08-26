package ir.node.ins;

import ir.IRVisitor;
import ir.info.IRBaseInfo;
import ir.node.IRType;
import sema.util.error.MyError;

public class IRReturn extends IRBaseInst {
  public IRType type;
  public IRBaseInfo val;

  @Override
  public <T> T accept(IRVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
