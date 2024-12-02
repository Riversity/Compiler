package codegen.node;

import java.util.ArrayList;

public class AsmBlock {
  public String label;
  public ArrayList<AsmNode> insts;

  public AsmBlock(String label) {
    this.label = label;
    this.insts = new ArrayList<>();
  }

  public void add(AsmNode ins) {
    insts.add(ins);
  }

  public void addAll(ArrayList<AsmNode> ins) {
    insts.addAll(ins);
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder(label + ":\n");
    for (AsmNode ins : insts) {
      s.append("  ").append(ins.toString()).append("\n");
    }
    return s.toString();
  }
}
