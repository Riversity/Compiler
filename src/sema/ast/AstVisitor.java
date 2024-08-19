package sema.ast;

import sema.ast.node.BaseNode;
import sema.ast.node.Program;
import sema.ast.node.def.ClassDef;
import sema.ast.node.def.FuncDef;
import sema.ast.node.def.VarDef;
import sema.ast.node.expr.*;
import sema.ast.node.stmt.*;
import sema.util.error.MyError;

public interface AstVisitor<T> {
  public T visit(BaseNode node) throws MyError;
  public T visit(Program node) throws MyError;

  public T visit(FuncDef node) throws MyError;
  public T visit(ClassDef node) throws MyError;
  public T visit(VarDef node) throws MyError;

  public T visit(AtomExpr node) throws MyError;
  public T visit(FStrExpr node) throws MyError;
  public T visit(MemberExpr node) throws MyError;
  public T visit(BracketExpr node) throws MyError;
  public T visit(FuncExpr node) throws MyError;
  public T visit(SelfExpr node) throws MyError;
  public T visit(UnaryExpr node) throws MyError;
  public T visit(NewExpr node) throws MyError;
  public T visit(BinaryExpr node) throws MyError;
  public T visit(LiteralML node) throws MyError;
  public T visit(AssignExpr node) throws MyError;
  public T visit(TernaryExpr node) throws MyError;

  public T visit(Block node) throws MyError;
  public T visit(VarDefStmt node) throws MyError;
  public T visit(IfElseStmt node) throws MyError;
  public T visit(BreakStmt node) throws MyError;
  public T visit(ContinueStmt node) throws MyError;
  public T visit(RetStmt node) throws MyError;
  public T visit(WhileStmt node) throws MyError;
  public T visit(ForStmt node) throws MyError;
  public T visit(PurExprStmt node) throws MyError;
  public T visit(EmptyStmt node) throws MyError;
}
