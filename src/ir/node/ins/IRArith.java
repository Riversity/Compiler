package ir.node.ins;

import ir.IRVisitor;
import ir.info.IRBaseInfo;
import ir.info.IRVarInfo;
import ir.node.IRNode;
import sema.util.error.MyError;

public class IRArith extends IRBaseInst {
  public IRVarInfo dest;
  public IRBaseInfo lhs, rhs;
  public String op;
  public boolean isCmp() {
    return op.equals("eq") || op.equals("ne") || op.equals("sgt") || op.equals("sge") || op.equals("slt") || op.equals("sle");
  }

  @Override
  public <T> T accept(IRVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
