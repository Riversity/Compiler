package ir.node.def;

import ir.IRVisitor;
import ir.info.IRVarInfo;
import ir.node.IRNode;
import sema.util.error.MyError;

public class IRGlobDef extends IRNode {
  public IRVarInfo info;

  public IRGlobDef(IRVarInfo info) {
    this.info = info;
  }

  @Override
  public <T> T accept(IRVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
