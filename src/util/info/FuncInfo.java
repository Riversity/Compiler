package util.info;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class FuncInfo extends BaseInfo {
  public TypeInfo type;
  public ArrayList<TypeInfo> params;
  public FuncInfo(String name, TypeInfo type, TypeInfo... params) {
    super(name);
    this.type = type;
    this.params = new ArrayList<TypeInfo>();
    this.params.addAll(Arrays.asList(params));
  }
  public FuncInfo(String name, TypeInfo type, List<TypeInfo> params) {
    super(name);
    this.type = type;
    this.params = new ArrayList<TypeInfo>();
    this.params.addAll(params);
  }
}
