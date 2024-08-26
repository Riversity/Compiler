package ir.info;

import ir.node.IRType;

public class IRConstInfo extends IRBaseInfo {
  // in name stores string of value
  public IRConstInfo(IRType type, String name) {
    super(type, name);
  }

  @Override
  public String toString() {
    return type.toString() + " " + name;
  }
}
