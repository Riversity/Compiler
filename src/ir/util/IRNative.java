package ir.util;

import ir.info.IRConstInfo;
import ir.node.IRType;
import ir.node.def.IRFuncDecl;

import java.util.ArrayList;
import java.util.List;

import static sema.util.Native.*;

public interface IRNative {
  IRType irVoidType = new IRType(voidType);
  IRType irIntType = new IRType(intType);
  IRConstInfo irZero = new IRConstInfo(irIntType, "0");
  IRType irBoolType = new IRType(boolType);
  IRConstInfo irFalse = new IRConstInfo(irBoolType, "0");
  IRType irPtrType = new IRType("ptr");

  IRFuncDecl irPrintFunc = new IRFuncDecl("print", irVoidType, new ArrayList<>(List.of(irPtrType)));
  IRFuncDecl irPrintlnFunc = new IRFuncDecl("println", irVoidType, new ArrayList<>(List.of(irPtrType)));
  IRFuncDecl irPrintIntFunc = new IRFuncDecl("printInt", irVoidType, new ArrayList<>(List.of(irIntType)));
  IRFuncDecl irPrintlnIntFunc = new IRFuncDecl("printlnInt", irVoidType, new ArrayList<>(List.of(irIntType)));
  IRFuncDecl irGetStringFunc = new IRFuncDecl("getString", irPtrType, new ArrayList<>());
  IRFuncDecl irGetIntFunc = new IRFuncDecl("getInt", irIntType, new ArrayList<>());
  IRFuncDecl irToStringFunc = new IRFuncDecl("toString", irPtrType, new ArrayList<>(List.of(irIntType)));
  IRFuncDecl irMallocFunc = new IRFuncDecl("malloc", irPtrType, new ArrayList<>(List.of(irIntType)));
  IRFuncDecl irArraySizeFunc = new IRFuncDecl("__array.size", irIntType, new ArrayList<>(List.of(irPtrType)));
  IRFuncDecl irStringLengthFunc = new IRFuncDecl("__string.length", irIntType, new ArrayList<>(List.of(irPtrType)));
  IRFuncDecl irStringSubstringFunc = new IRFuncDecl("__string.substring", irPtrType,
          new ArrayList<>(List.of(irPtrType, irIntType, irIntType)));
  IRFuncDecl irStringParseIntFunc = new IRFuncDecl("__string.parseInt", irIntType,
          new ArrayList<>(List.of(irPtrType)));
  IRFuncDecl irStringOrdFunc = new IRFuncDecl("__string.ord", irIntType,
          new ArrayList<>(List.of(irPtrType, irIntType)));
  IRFuncDecl irStringCompareFunc = new IRFuncDecl("__string.compare", irIntType,
          new ArrayList<>(List.of(irPtrType, irPtrType)));
  IRFuncDecl irStringConcatFunc = new IRFuncDecl("__string.concat", irPtrType,
          new ArrayList<>(List.of(irPtrType, irPtrType)));
  IRFuncDecl irStringCopyFunc = new IRFuncDecl("__string.copy", irVoidType,
          new ArrayList<>(List.of(irPtrType, irPtrType)));
  IRFuncDecl[] irBuiltInFuncs = { irPrintFunc, irPrintlnFunc, irPrintIntFunc, irPrintlnIntFunc,
          irGetStringFunc, irGetIntFunc, irToStringFunc, irMallocFunc, irArraySizeFunc, irStringLengthFunc,
          irStringSubstringFunc, irStringParseIntFunc, irStringOrdFunc, irStringCompareFunc, irStringConcatFunc,
          irStringCopyFunc };
}
