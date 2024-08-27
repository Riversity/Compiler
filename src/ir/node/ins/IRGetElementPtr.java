package ir.node.ins;

import ir.IRVisitor;
import ir.info.IRBaseInfo;
import ir.info.IRVarInfo;
import ir.node.IRType;
import sema.util.error.MyError;

public class IRGetElementPtr extends IRBaseInst {
  public IRVarInfo dest;
  public IRBaseInfo src;
  public boolean isMember;
  public IRType type;
  public IRBaseInfo index;

  @Override
  public <T> T accept(IRVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
