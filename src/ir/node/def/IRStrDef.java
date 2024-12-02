package ir.node.def;

import ir.IRVisitor;
import ir.info.IRVarInfo;
import ir.node.IRNode;
import sema.util.error.MyError;

public class IRStrDef extends IRGlobDef {
  public String str;

  public IRStrDef(IRVarInfo info, String str) {
    super(info);
    this.str = str;
  }

  @Override
  public <T> T accept(IRVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
