package ir.node.ins;

import ir.IRVisitor;
import ir.info.IRBaseInfo;
import ir.info.IRVarInfo;
import sema.util.error.MyError;

public class IRStore extends IRBaseInst {
  public IRBaseInfo src;
  public IRVarInfo dest;

  public <T> T accept(IRVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
