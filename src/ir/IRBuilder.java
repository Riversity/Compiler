package ir;

import sema.ast.AstVisitor;
import sema.ast.node.BaseNode;
import sema.ast.node.Program;
import sema.ast.node.def.ClassDef;
import sema.ast.node.def.FuncDef;
import sema.ast.node.def.VarDef;
import sema.ast.node.expr.*;
import sema.ast.node.stmt.*;
import sema.util.error.InternalError;
import sema.util.error.MyError;

public class IRBuilder implements AstVisitor<String> {
  public String visit(BaseNode node) throws MyError {
    throw new InternalError("What's the type?", node.pos);
  }
  public String visit(Program node) throws MyError {

  }

  public String visit(FuncDef node) throws MyError;
  public String visit(ClassDef node) throws MyError;
  public String visit(VarDef node) throws MyError;

  public String visit(AtomExpr node) throws MyError;
  public String visit(FStrExpr node) throws MyError;
  public String visit(MemberExpr node) throws MyError;
  public String visit(BracketExpr node) throws MyError;
  public String visit(FuncExpr node) throws MyError;
  public String visit(SelfExpr node) throws MyError;
  public String visit(UnaryExpr node) throws MyError;
  public String visit(NewExpr node) throws MyError;
  public String visit(BinaryExpr node) throws MyError;
  public String visit(LiteralML node) throws MyError;
  public String visit(AssignExpr node) throws MyError;
  public String visit(TernaryExpr node) throws MyError;

  public String visit(Block node) throws MyError;
  public String visit(VarDefStmt node) throws MyError;
  public String visit(IfElseStmt node) throws MyError;
  public String visit(BreakStmt node) throws MyError;
  public String visit(ContinueStmt node) throws MyError;
  public String visit(RetStmt node) throws MyError;
  public String visit(WhileStmt node) throws MyError;
  public String visit(ForStmt node) throws MyError;
  public String visit(PurExprStmt node) throws MyError;
  public String visit(EmptyStmt node) throws MyError;
}
