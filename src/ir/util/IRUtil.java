package ir.util;

import ir.node.IRType;
import sema.util.error.InternalError;
import sema.util.scope.BaseScope;
import sema.util.scope.GlobalScope;

import java.util.ArrayList;
import java.util.HashMap;

import static sema.util.Position.blankPos;

public class IRUtil {
  public static int tmpCnt = 0;
  public static int strCnt = 0;
  public static int blockCnt = 0;
  public static int ifCnt = 0;
  public static int loopCnt = 0;

  public static HashMap<String, Integer> sizeQuery;

  public static String getTmpVar() {
    return "%." + (tmpCnt++);
  }

  public static String getStrVar() {
    return "@string." + (strCnt++);
  }

  public static String getBlockLabel() {
    return "label." + (blockCnt++);
  }

  public static String getIfNumber() {
    return "if" + (ifCnt++);
  }

  public static String getLoopNumber() {
    return "loop" + (loopCnt++);
  }

  public static String rename(String name, BaseScope scope) {
    if(scope == null) throw new InternalError("Null scope!", blankPos);
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
            .replace("\\\"", "\\22").concat("\\00");
  }

  public static int stringConstLen(String str) {
    return str.replace("\\0A", "$").replace("\\22", "$")
            .replace("\\00", "$").replace("\\\\", "$").length();
  }

  public static String fHeadConvert(String str) {
    return str.substring(2, str.length() - 1).replace("\\n", "\\0A")
            .replace("\\\"", "\\22").replace("$$", "$").concat("\\00");
  }

  public static String fBodyConvert(String str) {
    return str.substring(1, str.length() - 1).replace("\\n", "\\0A")
            .replace("\\\"", "\\22").replace("$$", "$").concat("\\00");
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

  public static void calcSize(String name, ArrayList<IRType> list) {
    int sum = 0;
    for(var t : list) {
      var tmp = sizeQuery.get(t.typeName);
      if(tmp == null) {
        System.err.println(t.typeName);
        tmp = 0;
      }
      sum += tmp;
    }
    sizeQuery.put(name, sum);
  }

  public static Integer checkSize(String typeName) {
    var tmp = sizeQuery.get(typeName);
    if(tmp == null) {
      System.err.println(typeName);
      tmp = 0;
    }
    return tmp;
  }
}
