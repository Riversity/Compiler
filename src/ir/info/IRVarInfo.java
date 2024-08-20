package ir.info;

import ir.node.IRType;
import sema.util.error.InternalError;

import static sema.util.Position.blankPos;

public class IRVarInfo extends IRBaseInfo {
  public IRVarInfo(IRType type, String value) {
    super(type, value);
    if (!value.contains("@") && !value.contains("%")) {
      throw new InternalError("Variable name must start with @ or %", blankPos);
    }
  }
  @Override
  public String toString() {
    return type.toString() + " " + val;
  }
}
