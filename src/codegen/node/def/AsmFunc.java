package codegen.node.def;

import codegen.node.*;

import java.util.ArrayList;

public class AsmFunc {
  public String name;
  public ArrayList<AsmBlock> body;
  public AsmFunc(String name) {
    this.name = name;
    body = new ArrayList<>();
  }

  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append("  .globl ").append(name).append("\n");
    for (AsmBlock block : body) {
      str.append(block.toString()).append("\n");
    }
    str.append("\n");
    return str.toString();
  }
}
