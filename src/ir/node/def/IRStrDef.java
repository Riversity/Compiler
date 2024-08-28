package ir.node.def;

import ir.IRVisitor;
import ir.info.IRVarInfo;
import ir.node.IRNode;
import sema.util.error.MyError;

public class IRStrDef extends IRGlobDef {
  public String str;
  public int length;

  public IRStrDef(IRVarInfo info, String str) {
    super(info);
    this.str = str;
    length = str.length();
  }

  @Override
  public <T> T accept(IRVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
