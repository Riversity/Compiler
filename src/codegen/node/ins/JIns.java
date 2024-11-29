package codegen.node.ins;

import codegen.node.AsmNode;

public class JIns extends AsmNode {
  public String label;
  // jal x0, offset <=> j offset

  @Override
  public String toString() {
    return "j " + label;
  }
}
