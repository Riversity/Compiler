package ir.info;

import ir.node.IRType;

public abstract class IRBaseInfo {
  public IRType type;
  public String name;

  public IRBaseInfo(IRType type, String name) {
    this.type = type;
    this.name = name;
  }

  public abstract String toString();

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof IRBaseInfo) {
      return type.equals(((IRBaseInfo) obj).type) && name.equals(((IRBaseInfo) obj).name);
    }
    return false;
  }
}
