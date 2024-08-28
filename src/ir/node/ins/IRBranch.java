package ir.node.ins;

import ir.IRVisitor;
import ir.info.IRBaseInfo;
import ir.node.stmt.IRBlock;
import sema.util.error.MyError;

public class IRBranch extends IRBaseInst {
  public IRBaseInfo condition;
  public String trueLabel, falseLabel;

  public IRBlock start;
  public IRBlock trueEnd, falseEnd;

  @Override
  public <T> T accept(IRVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
