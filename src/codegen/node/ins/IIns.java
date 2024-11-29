package codegen.node.ins;

import codegen.node.AsmNode;

public class IIns extends AsmNode {
  public String op, rd, rs1;
  public int imm;

  @Override
  public String toString() {
    return op + " " + rd + ", " + rs1 + ", " + imm;
  }
}
