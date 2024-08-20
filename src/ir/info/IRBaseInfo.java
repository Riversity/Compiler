package ir.info;

import ir.node.IRType;

public abstract class IRBaseInfo {
  public IRType type;
  public String val;

  public IRBaseInfo(IRType type, String value) {
    this.type = type;
    this.val = value;
  }

  public abstract String toString();
}
