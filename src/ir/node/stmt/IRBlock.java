package ir.node.stmt;

import ir.IRVisitor;
import ir.node.ins.IRBaseInst;
import sema.util.error.MyError;

import java.util.ArrayList;

public class IRBlock extends IRStmt {
  public static int cnt = 0;
  public String label;
  public ArrayList<IRBaseInst> insts;
  public IRBaseInst exit;

  public IRBlock() {
    insts = new ArrayList<>();
    label = "label." + cnt;
  }

  @Override
  public <T> T accept(IRVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }

  public void addInst(IRBaseInst ins) {
    insts.add(ins);
  }
}
