package codegen.node;

import java.util.ArrayList;

public class AsmBlock {
  public String label;
  public ArrayList<AsmNode> insts;

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder(label + ":\n");
    for (AsmNode ins : insts) {
      s.append("  ").append(ins.toString()).append("\n");
    }
    return s.toString();
  }
}
