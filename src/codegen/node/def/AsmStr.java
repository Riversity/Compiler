package codegen.node.def;

public class AsmStr {
  public String label, val;
  public AsmStr (String label, String val) {
    this.label = label;
    this.val = val;
  }

  @Override
  public String toString() {
    return label + ":\n  .asciz " + val + "\n";
  }
}
