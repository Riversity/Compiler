package codegen.node.def;

public class AsmGlobVar {
  public String label;
  int val;
  public AsmGlobVar (String label, int val) {
    this.label = label;
    this.val = val;
  }

  @Override
  public String toString() {
    return label + ":\n  .word " + val + "\n";
  }
}
