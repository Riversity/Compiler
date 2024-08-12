package util.scope;

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
}
