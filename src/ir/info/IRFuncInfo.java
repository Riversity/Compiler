package ir.info;

import ir.node.IRType;

public class IRFuncInfo extends IRBaseInfo {
  public IRType returnType;

  public IRFuncInfo(String value, IRType returnType) {
    super(new IRType("function"), value);
    this.returnType = returnType;
  }

  @Override
  public String toString() {
    return "@" + val;
  }
}
