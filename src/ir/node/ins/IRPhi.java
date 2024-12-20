package ir.node.ins;

import ir.IRVisitor;
import ir.info.IRBaseInfo;
import ir.info.IRVarInfo;
import ir.node.stmt.IRBlock;
import org.antlr.v4.runtime.misc.Pair;
import sema.util.error.MyError;

import java.util.ArrayList;

public class IRPhi extends IRBaseInst {
  public IRVarInfo dest;
  public ArrayList<Pair<IRBaseInfo, IRBlock>> vals;

  @Override
  public <T> T accept(IRVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
