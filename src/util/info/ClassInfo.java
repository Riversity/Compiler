package util.info;

import java.util.HashMap;

public final class ClassInfo extends BaseInfo {
  public HashMap<String, VarInfo> vars;
  public HashMap<String, FuncInfo> funcs;
  //
  public ClassInfo(String name, FuncInfo... funcs) {
    super(name);
    this.vars = new HashMap<String, VarInfo>();
    this.funcs = new HashMap<String, FuncInfo>();
    for (var func : funcs) {
      this.funcs.put(func.name, func);
    }
  }
}
