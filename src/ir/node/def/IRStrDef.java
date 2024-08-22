package ir.node.def;

import ir.IRVisitor;
import ir.info.IRVarInfo;
import ir.node.IRNode;
import sema.util.error.MyError;

public class IRStrDef extends IRNode {
  public IRVarInfo info;
  public String value;
  public int length;

  @Override
  public <T> T accept(IRVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
