package codegen.node.ins;

import codegen.node.AsmNode;

public class CustomIns extends AsmNode {
  public String content;
  public CustomIns(String content) {
    this.content = content;
  }

  @Override
  public String toString() {
    return content;
  }
}
