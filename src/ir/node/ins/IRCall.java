package ir.node.ins;

import ir.IRVisitor;
import ir.info.IRBaseInfo;
import ir.info.IRVarInfo;
import ir.node.IRType;
import sema.util.error.MyError;

import java.util.ArrayList;

public class IRCall extends IRBaseInst {
  public IRVarInfo dest;
  public IRType type;
  public String funcName;
  public ArrayList<IRBaseInfo> args;

  @Override
  public <T> T accept(IRVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
