package ir.node.ins;

import ir.IRVisitor;
import ir.info.IRBaseInfo;
import ir.node.stmt.IRBlock;
import sema.util.error.MyError;

public class IRBranch extends IRBaseInst {
  public IRBaseInfo condition;

  public IRBlock trueEnd, falseEnd;

  public IRBranch() {}

  public IRBranch(IRBlock trueEnd, IRBlock falseEnd) {
    this.trueEnd = trueEnd;
    this.falseEnd = falseEnd;
  }

  public IRBranch(IRBlock trueEnd, IRBlock falseEnd, IRBaseInfo condition) {
    this.trueEnd = trueEnd;
    this.falseEnd = falseEnd;
    this.condition = condition;
  }

  public String trueLabel() {
    return trueEnd.label;
  }

  public String falseLabel() {
    return falseEnd.label;
  }

  @Override
  public <T> T accept(IRVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
