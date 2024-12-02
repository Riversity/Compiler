package codegen.node.ins;

import codegen.node.AsmNode;

public class IIns extends AsmNode {
  public String op, rd, rs1;
  public int imm;

  public IIns(String op, String rd, String rs1, int imm) {
    this.op = op;
    this.rd = rd;
    this.rs1 = rs1;
    this.imm = imm;
  }

  @Override
  public String toString() {
    if(imm > 2047 || imm < -2048) {
      String opnew;
      if(op.equals("sltiu")) opnew = "sltu";
      else opnew = op.substring(0, op.length() - 1);
      return "li" + " " + "t6" + ", " + imm + "\n  " +
              opnew + " " + rd + ", " + rs1 + ", " + "t6\n";
    }
    else {
      return op + " " + rd + ", " + rs1 + ", " + imm;
    }
  }
}
