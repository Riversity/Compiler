package ir.node.ins;

import ir.IRVisitor;
import ir.info.IRBaseInfo;
import ir.info.IRVarInfo;
import sema.util.error.MyError;

public class IRLoad extends IRBaseInst {
  public IRVarInfo dest;
  public IRVarInfo src;

  @Override
  public <T> T accept(IRVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
