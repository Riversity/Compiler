package codegen.node.ins;

import codegen.node.AsmNode;

public class LIns extends AsmNode {
  public String op, rd, rs1;
  public int imm;

  public LIns(String op, String rd, String rs1, int imm) {
    this.op = op;
    this.rd = rd;
    this.rs1 = rs1;
    this.imm = imm;
  }

  @Override
  public String toString() {
    // imm out of bound (11 bits)
    if(imm > 2047 || imm < -2048) {
      return "li" + " " + "t6" + ", " + imm + "\n  " +
              "add" + " " + "t6" + ", " + rs1 + ", " + "t6\n" +
              op + " " + rd + ", " + "0(t6)";
    }
    return op + " " + rd + ", " + imm + "(" + rs1 + ")";
  }
}
