package ir.node.stmt;

import ir.IRVisitor;
import ir.node.ins.IRBaseInst;
import ir.node.ins.IRJump;
import sema.util.error.MyError;

import java.util.ArrayList;

public class IRBlock extends IRExpr {
  public String label;
  public ArrayList<IRBaseInst> insts;
  public IRBaseInst exit;

  public IRBlock(String label) {
    insts = new ArrayList<>();
    this.label = label;
  }

  public IRBlock(String label, IRBaseInst exit) {
    insts = new ArrayList<>();
    this.label = label;
    this.exit = exit;
  }

  @Override
  public <T> T accept(IRVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }

  public void addInst(IRBaseInst ins) {
    insts.add(ins);
  }

  public IRBlock getNext() {
    if(exit instanceof IRJump) {
      return ((IRJump) exit).end;
    }
    return null;
  }
}
