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
import util.info.TypeInfo;
import util.info.VarInfo;
import util.scope.BaseScope;
import util.scope.ClassScope;
import util.scope.GlobalScope;

import java.util.Objects;

public class SymbolCollector implements AstVisitor<String> {
  private GlobalScope globalScope;
  private BaseScope curScope;

  public SymbolCollector() {
    globalScope = new GlobalScope();
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
    throw new InternalError("Why doesn't this node have type?", node.pos);
  }
  public String visit(Program node) throws MyError {
    node.scope = globalScope;
    enter(globalScope);
    for(var def : node.defs) {
      if(def instanceof ClassDef) {
        globalScope.insert(((ClassDef) def).info, node.pos);
      }
      else if(def instanceof FuncDef) {
        globalScope.insert(((FuncDef) def).info, node.pos);
      }
    }
    if(globalScope.getFunc("main") == null) {
      throw new SyntaxError("No main function", node.pos);
    }
    for(var def : node.defs) {
      if(def instanceof ClassDef || def instanceof FuncDef) {
        def.accept(this);
      }
    }
    exit();
    return "";
  }

  public String visit(FuncDef node) throws MyError {
    node.scope = new BaseScope(curScope, node.info);
    enter(node.scope);
    if(node.name.equals("main") && (!node.retType.info.name.equals("int") || node.retType.info.dimension != 0)) {
      throw new SyntaxError("Main function should have type int", node.pos);
    }
    if(!node.retType.info.name.equals("void")) checkType(node.retType.info, node.pos);
    for(var param : node.params) {
      curScope.insert(new VarInfo(param.b, param.a.info), node.pos);
    }
    exit();
    return "";
  }
  public String visit(ClassDef node) throws MyError {
    node.scope = new ClassScope(curScope, node.info);
    enter(node.scope);
    for(var varDef : node.vars) {
      for(var p : varDef.list) {
        curScope.insert(new VarInfo(p.a, varDef.type.info), node.pos);
      }
    }
    for(var func : node.funcs) {
      func.accept(this);
      if(func.name.equals(node.name)) {
        throw new SyntaxError("Constructor should not have type", func.pos);
      }
      curScope.insert(func.info, node.pos);
    }
    exit();
    return "";
  }
  public String visit(VarDef node) throws MyError {
    for(var v : node.list) {
      checkType(node.type.info, node.pos);
      curScope.insert(new VarInfo(v.a, node.type.info), node.pos);
    }
    return "";
  }

  public String visit(AtomExpr node) throws MyError {
    throw new InternalError("Unexpected in symbol collector", node.pos);
  }
  public String visit(FStrExpr node) throws MyError {
    throw new InternalError("Unexpected in symbol collector", node.pos);
  }
  public String visit(MemberExpr node) throws MyError {
    throw new InternalError("Unexpected in symbol collector", node.pos);
  }
  public String visit(BracketExpr node) throws MyError {
    throw new InternalError("Unexpected in symbol collector", node.pos);
  }
  public String visit(FuncExpr node) throws MyError {
    throw new InternalError("Unexpected in symbol collector", node.pos);
  }
  public String visit(SelfExpr node) throws MyError {
    throw new InternalError("Unexpected in symbol collector", node.pos);
  }
  public String visit(UnaryExpr node) throws MyError {
    throw new InternalError("Unexpected in symbol collector", node.pos);
  }
  public String visit(NewExpr node) throws MyError {
    throw new InternalError("Unexpected in symbol collector", node.pos);
  }
  public String visit(BinaryExpr node) throws MyError {
    throw new InternalError("Unexpected in symbol collector", node.pos);
  }
  public String visit(LiteralML node) throws MyError {
    throw new InternalError("Unexpected in symbol collector", node.pos);
  }
  public String visit(AssignExpr node) throws MyError {
    throw new InternalError("Unexpected in symbol collector", node.pos);
  }
  public String visit(TernaryExpr node) throws MyError {
    throw new InternalError("Unexpected in symbol collector", node.pos);
  }

  public String visit(Block node) throws MyError {
    throw new InternalError("Unexpected in symbol collector", node.pos);
  }
  public String visit(VarDefStmt node) throws MyError {
    throw new InternalError("Unexpected in symbol collector", node.pos);
  }
  public String visit(IfElseStmt node) throws MyError {
    throw new InternalError("Unexpected in symbol collector", node.pos);
  }
  public String visit(BreakStmt node) throws MyError {
    throw new InternalError("Unexpected in symbol collector", node.pos);
  }
  public String visit(ContinueStmt node) throws MyError {
    throw new InternalError("Unexpected in symbol collector", node.pos);
  }
  public String visit(RetStmt node) throws MyError {
    throw new InternalError("Unexpected in symbol collector", node.pos);
  }
  public String visit(WhileStmt node) throws MyError {
    throw new InternalError("Unexpected in symbol collector", node.pos);
  }
  public String visit(ForStmt node) throws MyError {
    throw new InternalError("Unexpected in symbol collector", node.pos);
  }
  public String visit(PurExprStmt node) throws MyError {
    throw new InternalError("Unexpected in symbol collector", node.pos);
  }
  public String visit(EmptyStmt node) throws MyError {
    throw new InternalError("Unexpected in symbol collector", node.pos);
  }
}
