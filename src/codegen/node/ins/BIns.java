package codegen.node.ins;

import codegen.node.AsmNode;

public class BIns extends AsmNode {
  public String op, rs1, rs2, label;
  //beq, bge, bgeu, blt, bltu, bne

  @Override
  public String toString() {
    return op + " " + rs1 + ", " + rs2 + ", " + label;
  }
}
