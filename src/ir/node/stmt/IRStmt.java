package ir.node.stmt;

import ir.info.*;
import ir.node.IRNode;
import ir.node.ins.*;

import java.util.ArrayList;

public abstract class IRStmt extends IRNode {
  public IRBaseInfo dest;
  public ArrayList<IRBaseInst> insts;
  // addr of dest?
}
