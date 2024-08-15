package util.scope;

import util.info.BaseInfo;
import util.info.VarInfo;

import java.util.HashMap;

public class BaseScope {
  public BaseScope parentScope;
  public HashMap<String, VarInfo> vars;
  public BaseInfo info;
  public BaseScope(BaseScope parentScope, BaseInfo info) {
    this.parentScope = parentScope;
    this.info = info;
    vars = new HashMap<String, VarInfo>();
  }

  public void insert(BaseInfo info) {
    if (info instanceof VarInfo) {
      vars.put(info.name, (VarInfo) info);
    } else {
      throw new RuntimeException("Wrong Call of Scope::insert");
    }
  }
  public VarInfo getVar(String name) {
    return vars.get(name);
  }
  // other functions
}
