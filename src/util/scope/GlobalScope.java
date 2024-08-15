package util.scope;

import util.Position;
import util.error.InternalError;
import util.error.MultipleDefinitions;
import util.info.*;

import java.util.HashMap;

import static util.Native.nativeFuncs;
import static util.Native.stringClass;

public class GlobalScope extends BaseScope {
  HashMap<String, FuncInfo> funcs;
  HashMap<String, ClassInfo> classes;
  public GlobalScope() {
    super(null, null);
    funcs = new HashMap<String, FuncInfo>();
    classes = new HashMap<String, ClassInfo>();
    for (FuncInfo func : nativeFuncs) {
      funcs.put(func.name, func);
    }
    classes.put("string", stringClass);
  }

  @Override
  public void insert(BaseInfo info, Position pos) {
    if(info instanceof VarInfo) {
      if(vars.put(info.name, (VarInfo) info) != null)
        throw new MultipleDefinitions("Variable names " + info.name + " repeat", pos);
      if(funcs.containsKey(info.name)) {
        throw new MultipleDefinitions("Variable and function names " + info.name + " repeat", pos);
      }
      if(classes.containsKey(info.name)) {
        throw new MultipleDefinitions("Variable and class names " + info.name + " repeat", pos);
      }
    }
    else if(info instanceof FuncInfo) {
      if(funcs.put(info.name, (FuncInfo) info) != null)
        throw new MultipleDefinitions("Function names " + info.name + " repeat", pos);
      if(vars.containsKey(info.name)) {
        throw new MultipleDefinitions("Function and variable names " + info.name + " repeat", pos);
      }
    }
    else if(info instanceof ClassInfo) {
      if(classes.put(info.name, (ClassInfo) info) != null)
        throw new MultipleDefinitions("Class names " + info.name + " repeat", pos);
      if(funcs.containsKey(info.name)) {
        throw new MultipleDefinitions("Class and function names " + info.name + " repeat", pos);
      }
    }
    else {
      throw new InternalError("Wrong Call of Scope::insert", pos);
    }
  }

  public FuncInfo getFunc(String name) {
    return funcs.get(name);
  }

  public ClassInfo getClass(String name) {
    return classes.get(name);
  }

  @Override
  public BaseInfo findRecur(String name) {
    if(vars.containsKey(name)) return vars.get(name);
    if(funcs.containsKey(name)) return funcs.get(name);
    if(parentScope != null) return parentScope.findRecur(name);
    return null;
  }
}
