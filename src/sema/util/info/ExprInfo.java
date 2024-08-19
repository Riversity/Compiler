package sema.util.info;

public final class ExprInfo extends BaseInfo {
  private TypeInfo type;
  private boolean isLeftValue;

  public ExprInfo(String name, TypeInfo type, boolean isLVal) {
    super(name);
    this.type = type;
    this.isLeftValue = isLVal;
  }
}
