package codegen.node.ins;

import codegen.node.AsmNode;

public class RIns extends AsmNode {
  public String op, rd, rs1, rs2;

  @Override
  public String toString() {
    return op + " " + rd + ", " + rs1 + ", " + rs2;
  }
}
