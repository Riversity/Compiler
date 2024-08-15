package util.scope;

import util.Position;
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
  public void insert(BaseInfo info) {
    if (info instanceof VarInfo) {
      if(vars.put(info.name, (VarInfo) info) != null)
        throw new MultipleDefinitions("Variable names repeat", new Position(0, 0));
    }
    else if (info instanceof FuncInfo) {
      if(funcs.put(info.name, (FuncInfo) info) != null)
        throw new MultipleDefinitions("Function names repeat", new Position(0, 0));
    }
    else if (info instanceof ClassInfo) {
      if(classes.put(info.name, (ClassInfo) info) != null)
        throw new MultipleDefinitions("Class names repeat", new Position(0, 0));
    }
    else {
      throw new RuntimeException("Wrong Call of GlobalScope::insert");
    }
  }

  public FuncInfo getFunc(String name) {
    return funcs.get(name);
  }

  public ClassInfo getClass(String name) {
    return classes.get(name);
  }
}
