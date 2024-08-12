package util;

import util.info.*;

import java.util.Arrays;
import java.util.Collections;
/* Built-in Types and Functions */

public interface Native {
  TypeInfo voidType = new TypeInfo("void", 0);
  TypeInfo intType = new TypeInfo("int", 0);
  TypeInfo boolType = new TypeInfo("bool", 0);
  TypeInfo stringType = new TypeInfo("string", 0);
  TypeInfo nullType = new TypeInfo("null", 0);
  TypeInfo thisType = new TypeInfo("this", 0);

  FuncInfo printFunc = new FuncInfo("print", voidType, stringType);
  FuncInfo printlnFunc = new FuncInfo("println", voidType, stringType);
  FuncInfo printIntFunc = new FuncInfo("printInt", voidType, intType);
  FuncInfo printlnIntFunc = new FuncInfo("printlnInt", voidType, intType);
  FuncInfo getStringFunc = new FuncInfo("getString", stringType);
  FuncInfo getIntFunc = new FuncInfo("getInt", intType);
  FuncInfo toStringFunc = new FuncInfo("toString", stringType, intType);

  FuncInfo[] nativeFuncs = { printFunc, printlnFunc, printIntFunc,
          printlnIntFunc, getStringFunc, getIntFunc, toStringFunc };

  FuncInfo arraySizeFunc = new FuncInfo("size", intType);

  FuncInfo stringLengthFunc = new FuncInfo("length", intType);
  FuncInfo stringSubstringFunc = new FuncInfo("substring", stringType, intType, intType);
  FuncInfo stringParseintFunc = new FuncInfo("parseInt", intType);
  FuncInfo stringOrdFunc = new FuncInfo("ord", intType, intType);

  ClassInfo stringClass = new ClassInfo("string", stringLengthFunc,
          stringSubstringFunc, stringParseintFunc, stringOrdFunc);
}
