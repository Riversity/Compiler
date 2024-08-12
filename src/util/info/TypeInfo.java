package util.info;

public class TypeInfo extends BaseInfo {
  private int dimension;
  private boolean isNative;
  public TypeInfo(String typeName, int arrayDim) {
    super(typeName);
    this.isNative = typeName.equals("int") || typeName.equals("bool") || typeName.equals("string") || typeName.equals("void") || typeName.equals("null");
    this.dimension = arrayDim;
  }

  @Override
  public boolean equals(Object otherInfo) {
    if (!(otherInfo instanceof TypeInfo)) {
      return false;
    }
    var other = (TypeInfo) otherInfo;
    /* null can be casted to array or class types */
    if (this.name.equals("null")) {
      return other.name.equals("null") || other.dimension > 0 || !other.isNative;
    }
    if (other.name.equals("null")) {
      return this.dimension > 0 || !this.isNative;
    }
    return this.name.equals(other.name) && this.dimension == other.dimension;
  }

  @Override
  public String toString() {
    return name + "[]".repeat(dimension);
  }
}