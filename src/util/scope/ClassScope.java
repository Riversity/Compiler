package util.scope;

import util.Position;
import util.error.MultipleDefinitions;
import util.info.*;

import java.util.HashMap;

public class ClassScope extends BaseScope {
  HashMap<String, FuncInfo> funcs;
  public ClassScope(BaseScope parent, ClassInfo info) {
    super(parent, info);
    funcs = new HashMap<String, FuncInfo>();
  }

  @Override
  public void insert(BaseInfo info) {
    if (info instanceof VarInfo) {
      if (vars.put(info.name, (VarInfo) info) != null)
        throw new MultipleDefinitions("Variable names repeat", new Position(0, 0));
    } else if (info instanceof FuncInfo) {
      if (funcs.put(info.name, (FuncInfo) info) != null)
        throw new MultipleDefinitions("Function names repeat", new Position(0, 0));
    } else {
      throw new RuntimeException("Wrong Call of GlobalScope::insert");
    }
  }

  public FuncInfo getFunc(String name) {
    return funcs.get(name);
  }
}
