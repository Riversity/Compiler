package ir.util;

import sema.util.scope.BaseScope;
import sema.util.scope.GlobalScope;

import java.util.ArrayList;

public class IRUtil {
  public static int tmpCnt = 0;

  public static String rename(String name, BaseScope scope) {
    if(scope instanceof GlobalScope) return "@" + name;
    String str = "";
    BaseScope curScope = scope;
    while(curScope.parentScope != null) {
      str = Integer.toHexString(curScope.hashCode()) + "." + str;
      curScope = curScope.parentScope;
    }
    return "%" + name + "." + str;
  }

  public static String stringConstConvert(String str) {
    return str.substring(1, str.length() - 1).replace("\\n", "\\0A")
            .replace("\\\"", "\\22");
  }

  public static <T> String arrayToString(ArrayList<T> list, String sep) {
    if(list == null || list.isEmpty()) return "";
    StringBuilder str = new StringBuilder();
    for(int i = 0; i < list.size() - 1; i++) {
      str.append(list.get(i).toString()).append(sep);
    }
    str.append(list.get(list.size() - 1).toString());
    return str.toString();
  }
}
