package ir.node.stmt;

import ir.IRVisitor;
import ir.info.*;
import ir.node.IRNode;
import ir.node.ins.*;
import sema.util.error.MyError;

import java.util.ArrayList;

public class IRStmt extends IRNode {
  public IRBaseInfo dest;
  public IRBaseInfo destAddr;

  @Override
  public <T> T accept(IRVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
