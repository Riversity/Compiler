package codegen.node;

import codegen.node.def.*;

import java.util.ArrayList;

public class AsmRoot extends AsmNode {
  public ArrayList<AsmFunc> funcs;
  public ArrayList<AsmGlobVar> vars;
  public ArrayList<AsmStr> strs;

  public AsmRoot() {
    funcs = new ArrayList<>();
    vars = new ArrayList<>();
    strs = new ArrayList<>();
  }

  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append("  .text\n");
    for (AsmFunc func : funcs) {
      str.append(func.toString());
    }
    str.append("\n  .data\n");
    for (AsmGlobVar var : vars) {
      str.append(var.toString());
    }
    str.append("\n  .rodata\n");
    for (AsmStr s : strs) {
      str.append(s.toString());
    }
    return str.toString();
  }
}
