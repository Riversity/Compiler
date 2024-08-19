package sema.util.scope;

import sema.util.info.BaseInfo;
import sema.util.Position;
import sema.util.error.InternalError;
import sema.util.error.MultipleDefinitions;
import sema.util.info.ClassInfo;
import sema.util.info.FuncInfo;
import sema.util.info.VarInfo;

import java.util.HashMap;

public class ClassScope extends BaseScope {
  HashMap<String, FuncInfo> funcs;
  public ClassScope(BaseScope parent, ClassInfo info) {
    super(parent, info);
    funcs = new HashMap<String, FuncInfo>();
  }

  @Override
  public void insert(BaseInfo info, Position pos) {
    if(info instanceof VarInfo) {
      if(vars.put(info.name, (VarInfo) info) != null)
        throw new MultipleDefinitions("Variable names " + info.name + " repeat", pos);
      if(funcs.containsKey(info.name)) {
        throw new MultipleDefinitions("Variable and function names " + info.name + " repeat", pos);
      }
    } else if(info instanceof FuncInfo) {
      if(funcs.put(info.name, (FuncInfo) info) != null)
        throw new MultipleDefinitions("Function names " + info.name + " repeat", pos);
      if(vars.containsKey(info.name)) {
        throw new MultipleDefinitions("Function and variable names " + info.name + " repeat", pos);
      }
    } else {
      throw new InternalError("Wrong Call of Scope::insert", pos);
    }
  }

  public FuncInfo getFunc(String name) {
    return funcs.get(name);
  }

  @Override
  public BaseInfo findRecur(String name) {
    if(vars.containsKey(name)) return vars.get(name);
    if(funcs.containsKey(name)) return funcs.get(name);
    if(parentScope != null) return parentScope.findRecur(name);
    return null;
  }
}
