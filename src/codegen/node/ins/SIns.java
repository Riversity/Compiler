package codegen.node.ins;

import codegen.node.AsmNode;

public class SIns extends AsmNode {
  public String op, rs1, rs2;
  public int imm;

  public SIns(String op, String rs1, String valToBeStored, int imm) {
    this.op = op;
    this.rs1 = rs1;
    this.rs2 = valToBeStored;
    this.imm = imm;
  }

  @Override
  public String toString() {
    // imm out of bound (11 bits)
    if(imm > 2047 || imm < -2048) {
      return "li" + " " + "t1" + ", " + imm + "\n  " +
              "add" + " " + "t1" + ", " + rs1 + ", " + "t1\n" +
              op + " " + rs2 + ", " + "0(t1)";
    }
    return op + " " + rs2 + ", " + imm + "(" + rs1 + ")";
  }
}
