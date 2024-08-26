package ir;

import ir.node.*;
import ir.node.def.*;
import ir.node.ins.*;
import ir.node.stmt.*;
import sema.ast.AstVisitor;
import sema.ast.node.BaseNode;
import sema.ast.node.Program;
import sema.ast.node.def.*;
import sema.ast.node.expr.*;
import sema.ast.node.stmt.*;
import sema.util.error.InternalError;
import sema.util.error.MyError;
import sema.util.scope.BaseScope;
import sema.util.scope.GlobalScope;

public class IRBuilder implements AstVisitor<IRNode> {
  GlobalScope globalScope;
  BaseScope curScope;

  public IRNode visit(BaseNode node) throws MyError {
    throw new InternalError("IRBuilder ASTNode type invalid", node.pos);
  }
  public IRNode visit(Program node) throws MyError {
    globalScope = node.scope;
    curScope = node.scope;
    
  }

  public IRNode visit(FuncDef node) throws MyError;
  public IRNode visit(ClassDef node) throws MyError;
  public IRNode visit(VarDef node) throws MyError;

  public IRNode visit(AtomExpr node) throws MyError;
  public IRNode visit(FStrExpr node) throws MyError;
  public IRNode visit(MemberExpr node) throws MyError;
  public IRNode visit(BracketExpr node) throws MyError;
  public IRNode visit(FuncExpr node) throws MyError;
  public IRNode visit(SelfExpr node) throws MyError;
  public IRNode visit(UnaryExpr node) throws MyError;
  public IRNode visit(NewExpr node) throws MyError;
  public IRNode visit(BinaryExpr node) throws MyError;
  public IRNode visit(LiteralML node) throws MyError;
  public IRNode visit(AssignExpr node) throws MyError;
  public IRNode visit(TernaryExpr node) throws MyError;

  public IRNode visit(Block node) throws MyError;
  public IRNode visit(VarDefStmt node) throws MyError;
  public IRNode visit(IfElseStmt node) throws MyError;
  public IRNode visit(BreakStmt node) throws MyError;
  public IRNode visit(ContinueStmt node) throws MyError;
  public IRNode visit(RetStmt node) throws MyError;
  public IRNode visit(WhileStmt node) throws MyError;
  public IRNode visit(ForStmt node) throws MyError;
  public IRNode visit(PurExprStmt node) throws MyError;
  public IRNode visit(EmptyStmt node) throws MyError;
}
