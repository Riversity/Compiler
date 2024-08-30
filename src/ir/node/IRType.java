package ir.node;

import sema.util.info.TypeInfo;

import static sema.util.Native.*;

public class IRType {
  public String typeName;

  public IRType(String typeName) {
    this.typeName = typeName;
  }

  public IRType(TypeInfo type) {
    // different while allocating
    if (type.equals(intType) && type.dimension == 0) {
      this.typeName = "i32";
    } else if (type.equals(boolType) && type.dimension == 0) {
      this.typeName = "i1";
    } else if (type.equals(voidType)) {
      this.typeName = "void";
    } else {
      this.typeName = "ptr";
    }
  }

  @Override
  public String toString() {
    return typeName;
  }

  @Override
  public boolean equals(Object obj) {
    if(obj instanceof IRType) {
      return typeName.equals(((IRType) obj).typeName);
    }
    return false;
  }
}
