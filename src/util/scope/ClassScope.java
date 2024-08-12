package util.scope;

import util.info.*;

import java.util.HashMap;

public class ClassScope extends BaseScope {
  HashMap<String, FuncInfo> funcs;
  public ClassScope(BaseScope parent, ClassInfo info) {
    super(parent, info);
    funcs = new HashMap<String, FuncInfo>();
  }
}
