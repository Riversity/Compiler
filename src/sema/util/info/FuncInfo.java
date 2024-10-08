package sema.util.info;

import java.util.ArrayList;
import java.util.Arrays;

public final class FuncInfo extends BaseInfo {
  public TypeInfo type;
  public ArrayList<TypeInfo> params;

  public FuncInfo(String name, TypeInfo type, TypeInfo... params) {
    super(name);
    this.type = type;
    this.params = new ArrayList<TypeInfo>();
    this.params.addAll(Arrays.asList(params));
  }
  public FuncInfo(FuncInfo other) {
    super(other.name);
    this.type = new TypeInfo(other.type);
    this.params = new ArrayList<>();
    this.params.addAll(other.params);
  }
  public FuncInfo(String name, TypeInfo type, ArrayList<TypeInfo> params) {
    super(name);
    this.type = type;
    this.params = params;
  }
}
