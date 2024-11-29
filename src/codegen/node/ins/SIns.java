package codegen.node.ins;

import codegen.node.AsmNode;

public class SIns extends AsmNode {
  public String op, rs1, rs2;
  public int imm;

  @Override
  public String toString() {
    // imm out of bound (11 bits)
    if(imm > 2047 || imm < -2048) {
      return "li" + " " + "t6" + ", " + imm + "\n  " +
              "add" + " " + "t6" + ", " + rs1 + ", " + "t6\n" +
              op + " " + rs2 + ", " + "0(t6)";
    }
    return op + " " + rs2 + ", " + imm + "(" + rs1 + ")";
  }
}
