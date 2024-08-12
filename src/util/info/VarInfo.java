package util.info;

public final class VarInfo extends BaseInfo {
  TypeInfo type;

  public VarInfo(String name, TypeInfo type) {
    super(name);
    this.type = type;
  }
}
