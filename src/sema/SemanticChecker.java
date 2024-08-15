package sema;

import ast.AstVisitor;
import ast.node.BaseNode;
import ast.node.Program;
import ast.node.def.ClassDef;
import ast.node.def.FuncDef;
import ast.node.def.VarDef;
import ast.node.expr.*;
import ast.node.stmt.*;
import util.Position;
import util.error.*;
import util.error.InternalError;
import util.info.*;
import util.scope.BaseScope;
import util.scope.GlobalScope;

import java.util.Objects;

import static util.Native.*;


public class SemanticChecker implements AstVisitor<String> {
  private GlobalScope globalScope;
  private BaseScope curScope;
  private int loopDepth;
  private String currentClass;

  public SemanticChecker() {
    loopDepth = 0;
    currentClass = null;
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
    for(var stmt : node.body.statements) {
      stmt.accept(this);
    }
    // if(!node.name.equals("main") && !node.retType.info.name.equals("void")) {
      // Check return
    // }
    exit();
    return "";
  }
  public String visit(ClassDef node) throws MyError {
    enter(node.scope);
    currentClass = node.name;
    node.constructor.accept(this);
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
      curScope.insert(new VarInfo(p.a, node.type.info), node.pos);
      if(p.b != null) {
        p.b.accept(this);
        if(!node.type.info.equals(p.b.info)) {
          throw new TypeMismatch("Cannot assign " + p.b.info.name + " to " + p.a, node.pos);
        }
      }
    }
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
      if(info instanceof FuncInfo) {
        node.info = info;
      }
      else if(info instanceof VarInfo) {
        node.info = info;
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
  public String visit(FStrExpr node) throws MyError;
  public String visit(MemberExpr node) throws MyError;
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
  public String visit(FuncExpr node) throws MyError;
  public String visit(SelfExpr node) throws MyError;
  public String visit(UnaryExpr node) throws MyError;
  public String visit(NewExpr node) throws MyError;
  public String visit(BinaryExpr node) throws MyError;
  public String visit(LiteralML node) throws MyError;
  public String visit(AssignExpr node) throws MyError;
  public String visit(TernaryExpr node) throws MyError;

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
  public String visit(IfElseStmt node) throws MyError;
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

  }
  public String visit(WhileStmt node) throws MyError {

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
