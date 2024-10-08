package sema;

import sema.ast.AstVisitor;
import sema.ast.node.BaseNode;
import sema.ast.node.Program;
import sema.ast.node.def.ClassDef;
import sema.ast.node.def.FuncDef;
import sema.ast.node.def.VarDef;
import sema.ast.node.expr.*;
import sema.ast.node.stmt.*;
import sema.util.Position;
import sema.util.error.*;
import sema.util.error.InternalError;
import sema.util.info.*;
import sema.util.scope.BaseScope;
import sema.util.scope.GlobalScope;

import java.util.Objects;

import static java.lang.Math.*;
import static sema.util.Native.*;


public class SemanticChecker implements AstVisitor<String> {
  private GlobalScope globalScope;
  private BaseScope curScope;
  private int loopDepth;
  private String currentClass;
  private TypeInfo funcRetType;
  private boolean isRet;

  public SemanticChecker() {
    loopDepth = 0;
    currentClass = null;
    funcRetType = null;
    isRet = false;
  }
  public void enter(BaseScope scope) {
    if(globalScope == null) globalScope = (GlobalScope) scope;
    curScope = scope;
  }
  public void exit() {
    curScope = curScope.parentScope;
  }
  public void checkType(TypeInfo info, Position pos) throws MyError {
    if(!(info.isNative && !Objects.equals(info.name, "null") &&
            !Objects.equals(info.name, "void")) && (globalScope.getClass(info.name) == null)) {
      throw new UndefinedIdentifier("Check type failed", pos);
    }
  }

  public String visit(BaseNode node) throws MyError {
    throw new InternalError("No type error", node.pos);
  }

  public String visit(Program node) throws MyError {
    enter(node.scope);
    for(var v : node.defs) {
      v.accept(this);
    }
    exit();
    return "";
  }

  public String visit(FuncDef node) throws MyError {
    enter(node.scope);
    funcRetType = node.retType.info;
    for(var stmt : node.body.statements) {
      stmt.accept(this);
    }
    if(!node.name.equals("main") && !node.retType.info.name.equals("void")) {
      if(!isRet) throw new MissingReturnStatement("Missing Return Statement", node.pos);
    }
    isRet = false;
    funcRetType = null;
    exit();
    return "";
  }
  public String visit(ClassDef node) throws MyError {
    enter(node.scope);
    currentClass = node.name;
    funcRetType = voidType;
    node.constructor.accept(this);
    funcRetType = null;
    for(var func : node.funcs) {
      func.accept(this);
    }
    currentClass = null;
    exit();
    return "";
  }
  public String visit(VarDef node) throws MyError {
    checkType(node.type.info, node.pos);
    for(var p : node.list) {
      if(p.b != null) {
        p.b.accept(this);
        if(!node.type.info.equals(p.b.info)) {
          throw new TypeMismatch("Cannot assign " + p.b.info.name + " type to " + p.a, node.pos);
        }
      }
      curScope.insert(new VarInfo(p.a, node.type.info), node.pos);
    }
    node.scope = curScope;
    return "";
  }

  public String visit(AtomExpr node) throws MyError {
    node.isLValue = false;
    if(node.atomType == AtomExpr.Type.NULL) {
      node.info = new TypeInfo(nullType);
    }
    else if(node.atomType == AtomExpr.Type.TF) {
      node.info = new TypeInfo(boolType);
    }
    else if(node.atomType == AtomExpr.Type.DEC) {
      node.info = new TypeInfo(intType);
    }
    else if(node.atomType == AtomExpr.Type.STR) {
      node.info = new TypeInfo(stringType);
    }
    else if(node.atomType == AtomExpr.Type.ID) {
      BaseInfo info = curScope.findRecur(node.val);
      node.whereToFind = curScope.findRecurScope(node.val);
      if(info instanceof FuncInfo) {
        node.info = info;
      }
      else if(info instanceof VarInfo) {
        node.info = ((VarInfo) info).type;
        node.isLValue = true;
      } else {
        throw new UndefinedIdentifier("Symbol " + node.val +" not found", node.pos);
      }
    }
    else if(node.atomType == AtomExpr.Type.THIS) {
      if(currentClass != null) {
        node.info = new TypeInfo(currentClass, 0);
      }
      else {
        throw new SyntaxError("This used outside a class", node.pos);
      }
    }
    else throw new InternalError("Atom type missing", node.pos);
    return "";
  }
  public String visit(FStrExpr node) throws MyError {
    node.isLValue = false;
    node.info = new TypeInfo(stringType);
    if(node.fAtom != null) {
      return "";
    }
    for(var e : node.exprs) {
      e.accept(this);
      if(!boolType.equals(e.info) && !intType.equals(e.info) && !stringType.equals(e.info)) {
        throw new InvalidType("FString cannot have " + e.info.name + " as expression", node.pos);
      }
    }
    return "";
  }
  public String visit(MemberExpr node) throws MyError {
    node.expr.accept(this);
    if(!(node.expr.info instanceof TypeInfo)) {
      throw new InvalidType("Cannot call member of a function", node.pos);
    }
    else {
      if(((TypeInfo) node.expr.info).dimension > 0) {
        if(!Objects.equals(node.member, "size")) {
          throw new InvalidType("Cannot call member " + node.member +" of an array", node.pos);
        }
        else {
          node.isArraySize = true;
          node.info = new FuncInfo(arraySizeFunc);
          node.isLValue = false;
        }
      }
      else {
        ClassInfo classInfo = globalScope.getClass(node.expr.info.name);
        if(classInfo == null) {
          throw new UndefinedIdentifier("Class symbol " + node.expr.info.name + " not found", node.pos);
        }
        node.classInfo = classInfo;
        VarInfo memberVarInfo = classInfo.vars.get(node.member);
        if(memberVarInfo != null) {
          node.info = new TypeInfo(memberVarInfo.type);
          node.isLValue = true;
        }
        else {
          FuncInfo memberFuncInfo = classInfo.funcs.get(node.member);
          if(memberFuncInfo != null) {
            node.info = new FuncInfo(memberFuncInfo);
            node.isLValue = false;
          }
          else {
            throw new UndefinedIdentifier("Member symbol " + node.member + " not found", node.pos);
          }
        }
      }
    }
    return "";
  }
  public String visit(BracketExpr node) throws MyError {
    node.array.accept(this);
    if(node.array.info instanceof TypeInfo) {
      if(((TypeInfo) node.array.info).dimension <= 0) {
        throw new DimensionOutOfBound("Dimension out of bound", node.pos);
      }
      node.index.accept(this);
      if(!intType.equals(node.index.info)) {
        throw new InvalidType("Index not integer", node.pos);
      }
      node.info = new TypeInfo(node.array.info.name, ((TypeInfo) node.array.info).dimension - 1);
      node.isLValue = node.array.isLValue;
    }
    else {
      throw new SyntaxError("Wrong bracket call to non-array", node.pos);
    }
    return "";
  }
  public String visit(FuncExpr node) throws MyError {
    node.func.accept(this);
    if(!(node.func.info instanceof FuncInfo)) {
      throw new InvalidType("Expected a function", node.pos);
    }
    else {
      FuncInfo f = (FuncInfo) node.func.info;
      if(f.params.size() != node.args.size()) {
        throw new SyntaxError("Wrong number of arguments", node.pos);
      }
      for(int i = 0; i < f.params.size(); ++i) {
        node.args.get(i).accept(this);
        if(!f.params.get(i).equals(node.args.get(i).info)) {
          throw new TypeMismatch("Argument " + (i + 1) + " of wrong type", node.pos);
        }
      }
      node.info = new TypeInfo(f.type);
      node.isLValue = false;
    }
    return "";
  }
  public String visit(SelfExpr node) throws MyError {
    node.object.accept(this);
    if(!intType.equals(node.object.info)) {
      throw new InvalidType("Only applies to integer", node.pos);
    }
    if(!node.object.isLValue) {
      throw new InvalidType("Only applies to lvalue", node.pos);
    }
    node.info = new TypeInfo(intType);
    node.isLValue = false;
    return "";
  }
  public String visit(UnaryExpr node) throws MyError {
    node.isLValue = false;
    node.object.accept(this);
    if(node.op.equals("!")) {
      if(!boolType.equals(node.object.info)) {
        throw new InvalidType("Only applies to bool", node.pos);
      }
      node.info = new TypeInfo(boolType);
    }
    else {
      if(!intType.equals(node.object.info)) {
        throw new InvalidType("Only applies to integer", node.pos);
      }
      if(node.op.equals("++") || node.op.equals("--")) {
        node.isLValue = true;
        if(!node.object.isLValue) {
          throw new InvalidType("Only applies to lvalue", node.pos);
        }
      }
      node.info = new TypeInfo(intType);
    }
    return "";
  }
  public String visit(NewExpr node) throws MyError {
    node.info = node.type.info;
    node.isLValue = true;
    checkType(node.type.info, node.pos);
    for(var expr : node.type.width) {
      expr.accept(this);
      if(!intType.equals(expr.info)) {
        throw new InvalidType("Index must be integer", node.pos);
      }
    }
    if(node.type.info.isNative && node.type.info.dimension == 0) {
      throw new InvalidType("Cannot create native type", node.pos);
    }
    if(node.init != null) {
      node.init.accept(this);
      if(!node.type.info.equals(node.init.info)) {
        throw new TypeMismatch("New type mismatch", node.pos);
      }
    }
    return "";
  }
  public String visit(BinaryExpr node) throws MyError {
    node.isLValue = false;
    node.lhs.accept(this);
    node.rhs.accept(this);
    if(!(node.lhs.info instanceof TypeInfo) ||
       !(node.rhs.info instanceof TypeInfo)) {
      throw new InvalidType("Cannot apply binary operation on funcs", node.pos);
    }
    TypeInfo lType = (TypeInfo) node.lhs.info;
    TypeInfo rType = (TypeInfo) node.rhs.info;
    if(!lType.equals(rType)) {
      throw new TypeMismatch("Binary type mismatch", node.pos);
    }
    if(node.op.equals("<") || node.op.equals("<=") ||
       node.op.equals(">") || node.op.equals(">=")) {
      node.info = new TypeInfo(boolType);
      if(!lType.equals(stringType) && !lType.equals(intType)) {
        throw new InvalidType("Expected string or int", node.pos);
      }
    }
    else if(node.op.equals("==") || node.op.equals("!=")) {
      node.info = new TypeInfo(boolType);
    }
    else if(node.op.equals("&&") || node.op.equals("||")) {
      if(!lType.equals(boolType)) {
        throw new InvalidType("Expected bool", node.pos);
      }
      node.info = new TypeInfo(boolType);
    }
    else if(node.op.equals("+")) {
      if(!lType.equals(stringType) && !lType.equals(intType)) {
        throw new InvalidType("Expected int or string", node.pos);
      }
      node.info = new TypeInfo(lType);
    }
    else {
      if(!lType.equals(intType)) {
        throw new InvalidType("Expected int", node.pos);
      }
      node.info = new TypeInfo(intType);
    }
    return "";
  }
  public String visit(LiteralML node) throws MyError {
    int dim;
    String typeName = "int";
    if(node.atomList != null) {
      dim = 1;
      switch(node.type) {
        case TF: typeName = "bool"; break;
        case STR: typeName = "string"; break;
        case DEC: typeName = "int"; break;
        case ANY: dim = -1; typeName = "null"; break;
      }
    }
    else if(!node.list.isEmpty()) {
      for(var v : node.list) {
        v.accept(this);
      }
      int tmpMin = ((TypeInfo) node.list.get(0).info).dimension;
      int tmpMax = tmpMin;
      String tmpType = "null";
      for(var v : node.list) {
        tmpMax = max(tmpMax, ((TypeInfo) v.info).dimension);
        tmpMin = min(tmpMin, ((TypeInfo) v.info).dimension);
        if(!v.info.name.equals("null")) tmpType = v.info.name;
      }
      if(tmpMax < 0) {
        dim = tmpMin - 1;
        typeName = "null";
      }
      else {
        if(tmpMin < 0 && -tmpMin > tmpMax) throw new DimensionOutOfBound("LiteralML blank dimension mismatch", node.pos);
        for(var v : node.list) {
          if(((TypeInfo) v.info).dimension > 0 && ((TypeInfo) v.info).dimension != tmpMax) {
            throw new DimensionOutOfBound("LiteralML dimension mismatch", node.pos);
          }
          if(!v.info.name.equals(tmpType) && !v.info.name.equals("null"))
            throw new TypeMismatch("LiteralML type mismatch", node.pos);
        }
        typeName = tmpType;
        dim = tmpMax + 1;
      }
    }
    else {
      throw new InternalError("LiteralMultiList empty unexpected", node.pos);
    }
    node.info = new TypeInfo(typeName, dim);
    node.isLValue = false;
    return "";
  }
  public String visit(AssignExpr node) throws MyError {
    node.lhs.accept(this);
    node.rhs.accept(this);
    if(!(node.lhs.info instanceof TypeInfo) || !(node.rhs.info instanceof TypeInfo)) {
      throw new InvalidType("Cannot apply assign on funcs", node.pos);
    }
    TypeInfo lType = (TypeInfo) node.lhs.info;
    TypeInfo rType = (TypeInfo) node.rhs.info;
    if(!lType.equals(rType)) {
      throw new TypeMismatch("Assign type mismatch", node.pos);
    }
    if(!node.lhs.isLValue) {
      throw new SyntaxError("Cannot assign rvalue", node.pos);
    }
    node.info = new TypeInfo(lType);
    node.isLValue = true;
    return "";
  }
  public String visit(TernaryExpr node) throws MyError {
    node.condition.accept(this);
    node.trueBranch.accept(this);
    node.falseBranch.accept(this);
    if(!boolType.equals(node.condition.info)) {
      throw new InvalidType("Ternary condition must be bool", node.pos);
    }
    if(!(node.trueBranch.info instanceof TypeInfo) ||
       !(node.falseBranch.info instanceof TypeInfo)) {
      throw new InvalidType("Cannot apply ternary on funcs", node.pos);
    }
    TypeInfo tType = (TypeInfo) node.trueBranch.info;
    TypeInfo fType = (TypeInfo) node.falseBranch.info;
    if(!tType.equals(fType)) {
      throw new TypeMismatch("Ternary type mismatch", node.pos);
    }
    node.info = new TypeInfo((TypeInfo) node.trueBranch.info);
    node.isLValue = false;
    return "";
  }

  public String visit(Block node) throws MyError {
    node.scope = new BaseScope(curScope, null);
    enter(node.scope);
    for(var stmt : node.statements) {
      stmt.accept(this);
    }
    exit();
    return "";
  }
  public String visit(VarDefStmt node) throws MyError {
    node.def.accept(this);
    return "";
  }
  public String visit(IfElseStmt node) throws MyError {
    node.condition.accept(this);
    if(!boolType.equals(node.condition.info)) {
      throw new InvalidType("If condition not bool", node.pos);
    }
    node.trueScope = new BaseScope(curScope, null);
    enter(node.trueScope);
    if(node.trueBranch instanceof Block) {
      for(var stmt : ((Block) node.trueBranch).statements) {
        stmt.accept(this);
      }
    }
    else node.trueBranch.accept(this);
    exit();
    if(node.falseBranch != null) {
      node.falseScope = new BaseScope(curScope, null);
      enter(node.falseScope);
      if(node.falseBranch instanceof Block) {
        for(var stmt : ((Block) node.falseBranch).statements) {
          stmt.accept(this);
        }
      }
      else node.falseBranch.accept(this);
      exit();
    }
    return "";
  }
  public String visit(BreakStmt node) throws MyError {
    if(loopDepth <= 0) {
      throw new InvalidControlFlow("Break should be used in a loop", node.pos);
    }
    return "";
  }
  public String visit(ContinueStmt node) throws MyError {
    if(loopDepth <= 0) {
      throw new InvalidControlFlow("Continue should be used in a loop", node.pos);
    }
    return "";
  }
  public String visit(RetStmt node) throws MyError {
    isRet = true;
    if(funcRetType == null) {
      throw new InvalidControlFlow("Return outside function", node.pos);
    }
    else {
      if(funcRetType.equals(voidType)) {
        if(node.retVal != null) {
          throw new TypeMismatch("Return should be void", node.pos);
        }
      }
      else {
        node.retVal.accept(this);
        if(!funcRetType.equals(node.retVal.info)) {
          throw new TypeMismatch("Return type mismatch", node.pos);
        }
      }
    }
    return "";
  }
  public String visit(WhileStmt node) throws MyError {
    node.scope = new BaseScope(curScope, null);
    enter(node.scope);
    ++loopDepth;
    node.condition.accept(this);
    if(!boolType.equals(node.condition.info)) {
      throw new InvalidType("Loop condition must be bool", node.pos);
    }
    if(node.body instanceof Block) {
      for(var stmt : ((Block) node.body).statements) {
        stmt.accept(this);
      }
    }
    else{
      node.body.accept(this);
    }
    --loopDepth;
    exit();
    return "";
  }
  public String visit(ForStmt node) throws MyError {
    node.scope = new BaseScope(curScope, null);
    enter(node.scope);
    ++loopDepth;
    if(node.initExpr != null) node.initExpr.accept(this);
    else if(node.initStmt != null) node.initStmt.accept(this);
    if(node.condition != null) {
      node.condition.accept(this);
      if(!boolType.equals(node.condition.info)) {
        throw new InvalidType("Loop condition must be bool", node.pos);
      }
    }
    if(node.step != null) node.step.accept(this);
    if(node.body instanceof Block) {
      for(var stmt : ((Block) node.body).statements) {
        stmt.accept(this);
      }
    }
    else{
      node.body.accept(this);
    }
    --loopDepth;
    exit();
    return "";
  }
  public String visit(PurExprStmt node) throws MyError {
    node.expr.accept(this);
    if(node.expr.info instanceof FuncInfo) {
      throw new SyntaxError("Function call without arguments", node.pos);
    }
    return "";
  }
  public String visit(EmptyStmt node) throws MyError {
    return "";
  }
}
