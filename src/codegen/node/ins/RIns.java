package codegen.node.ins;

import codegen.node.AsmNode;

public class RIns extends AsmNode {
  public String op, rd, rs1, rs2;

  public RIns(String op, String rd, String rs1, String rs2) {
    this.op = op;
    this.rd = rd;
    this.rs1 = rs1;
    this.rs2 = rs2;
  }

  @Override
  public String toString() {
    return op + " " + rd + ", " + rs1 + ", " + rs2;
  }
}
