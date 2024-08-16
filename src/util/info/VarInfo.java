package util.info;

public final class VarInfo extends BaseInfo {
  public TypeInfo type;

  public VarInfo(String name, TypeInfo type) {
    super(name);
    this.type = new TypeInfo(type);
  }
}
