package sema.util.info;

public class TypeInfo extends BaseInfo {
  public int dimension;
  public boolean isNative;
  public TypeInfo(String typeName, int arrayDim) {
    super(typeName);
    this.dimension = arrayDim;
    this.isNative = typeName.equals("int") || typeName.equals("bool") || typeName.equals("string") || typeName.equals("void") || typeName.equals("null");
  }
  public TypeInfo(TypeInfo other) {
    super(other.name);
    this.dimension = other.dimension;
    this.isNative = other.isNative;
  }

  @Override
  public boolean equals(Object otherInfo) {
    if(!(otherInfo instanceof TypeInfo)) {
      return false;
    }
    var other = (TypeInfo) otherInfo;
    /* null can be casted to array or class types */
    if(this.name.equals("null")) {
      return other.name.equals("null") || other.dimension > 0 || !other.isNative;
    }
    if(other.name.equals("null")) {
      if(other.dimension == 0) {
        return this.dimension > 0 || !this.isNative;
      }
      else {
        /* As in LiteralML initialization */
        return this.isNative && other.dimension >= -this.dimension;
      }
    }
    return this.name.equals(other.name) && this.dimension == other.dimension;
  }

  @Override
  public String toString() {
    return name + "[]".repeat(dimension);
  }
}
