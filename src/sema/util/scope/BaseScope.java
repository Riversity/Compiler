package sema.util.scope;

import sema.util.Position;
import sema.util.info.BaseInfo;
import sema.util.error.InternalError;
import sema.util.error.MultipleDefinitions;
import sema.util.info.VarInfo;

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

  public void insert(BaseInfo info, Position pos) {
    if(info instanceof VarInfo) {
      if(vars.put(info.name, (VarInfo) info) != null)
        throw new MultipleDefinitions("Variable names " + info.name + " repeat", pos);
    } else {
      throw new InternalError("Wrong Call of Scope::insert", pos);
    }
  }
  public VarInfo getVar(String name) {
    return vars.get(name);
  }
  // other functions
  public BaseInfo findRecur(String name) {
    if(vars.containsKey(name)) return vars.get(name);
    if(parentScope != null) return parentScope.findRecur(name);
    return null;
  }

  public BaseScope findRecurScope(String name) {
    if(vars.containsKey(name)) return this;
    if(parentScope != null) return parentScope.findRecurScope(name);
    return null;
  }
}
