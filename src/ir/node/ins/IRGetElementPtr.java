package ir.node.ins;

import ir.IRVisitor;
import ir.info.IRBaseInfo;
import ir.info.IRVarInfo;
import sema.util.error.MyError;

public class IRGetElementPtr extends IRBaseInst {
  public IRVarInfo dest;
  public IRBaseInfo src;
  public boolean isMember;
  public String type; // for types like [8 x i1]
  public IRBaseInfo index;

  @Override
  public <T> T accept(IRVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
