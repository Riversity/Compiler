package ir.util;

import sema.util.scope.BaseScope;
import sema.util.scope.GlobalScope;

public class IRNameConvert {
  String rename(String name, BaseScope scope) {
    if(scope instanceof GlobalScope) return "@" + name;
    String str = "";
    BaseScope curScope = scope;
    while(curScope.parentScope != null) {
      str = Integer.toHexString(curScope.hashCode()) + "." + str;
      curScope = curScope.parentScope;
    }
    return "%Var." + str;
  }

  String stringConstConvert(String str) {
    return str.substring(1, str.length() - 1).replace("\\n", "\\0A")
            .replace("\\\"", "\\22");
  }
}
