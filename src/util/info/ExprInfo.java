package util.info;

public final class ExprInfo extends BaseInfo {
  private BaseInfo type;
  private boolean isLeftValue;

  public ExprInfo(String name, BaseInfo type, boolean isLVal) {
    super(name);
    this.type = type;
    this.isLeftValue = isLVal;
  }
}