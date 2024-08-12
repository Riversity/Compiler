package ast;

import ast.node.*;
import parser.MxBaseVisitor;
import parser.MxParser;
import util.Position;

public class AstBuilder extends MxBaseVisitor<BaseNode> {
  @Override
  public BaseNode visitProgram(MxParser.ProgramContext ctx) {
    var program = new Root(new Position(ctx.start));
    //
  }
  @Override
  public BaseNode 
  visitBlock(MxParser.BlockContext ctx) {
    //
  }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.</p>
   */
  @Override
  public BaseNode 
  visitBlockStmt(MxParser.BlockStmtContext ctx) { return visitChildren(ctx); }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.</p>
   */
  @Override
  public BaseNode 
  visitVarDefStmt(MxParser.VarDefStmtContext ctx) { return visitChildren(ctx); }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.</p>
   */
  @Override
  public BaseNode 
  visitIfElseStmt(MxParser.IfElseStmtContext ctx) { return visitChildren(ctx); }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.</p>
   */
  @Override
  public BaseNode 
  visitBreakStmt(MxParser.BreakStmtContext ctx) { return visitChildren(ctx); }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.</p>
   */
  @Override
  public BaseNode 
  visitContinueStmt(MxParser.ContinueStmtContext ctx) { return visitChildren(ctx); }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.</p>
   */
  @Override
  public BaseNode 
  visitReturnStmt(MxParser.ReturnStmtContext ctx) { return visitChildren(ctx); }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.</p>
   */
  @Override
  public BaseNode 
  visitWhileStmt(MxParser.WhileStmtContext ctx) { return visitChildren(ctx); }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.</p>
   */
  @Override
  public BaseNode 
  visitForStmt(MxParser.ForStmtContext ctx) { return visitChildren(ctx); }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.</p>
   */
  @Override
  public BaseNode 
  visitPurExprStmt(MxParser.PurExprStmtContext ctx) { return visitChildren(ctx); }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.</p>
   */
  @Override
  public BaseNode 
  visitEmptyStmt(MxParser.EmptyStmtContext ctx) { return visitChildren(ctx); }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.</p>
   */
  @Override
  public BaseNode 
  visitTypeName(MxParser.TypeNameContext ctx) { return visitChildren(ctx); }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.</p>
   */
  @Override
  public BaseNode 
  visitBracket(MxParser.BracketContext ctx) { return visitChildren(ctx); }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.</p>
   */
  @Override
  public BaseNode 
  visitVarDef(MxParser.VarDefContext ctx) { return visitChildren(ctx); }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.</p>
   */
  @Override
  public BaseNode 
  visitVarTerm(MxParser.VarTermContext ctx) { return visitChildren(ctx); }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.</p>
   */
  @Override
  public BaseNode 
  visitFuncDef(MxParser.FuncDefContext ctx) { return visitChildren(ctx); }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.</p>
   */
  @Override
  public BaseNode 
  visitParameterList(MxParser.ParameterListContext ctx) { return visitChildren(ctx); }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.</p>
   */
  @Override
  public BaseNode 
  visitClassDef(MxParser.ClassDefContext ctx) { return visitChildren(ctx); }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.</p>
   */
  @Override
  public BaseNode 
  visitArgumentList(MxParser.ArgumentListContext ctx) { return visitChildren(ctx); }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.</p>
   */
  @Override
  public BaseNode 
  visitSelfExpr(MxParser.SelfExprContext ctx) { return visitChildren(ctx); }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.</p>
   */
  @Override
  public BaseNode 
  visitFunctionExpr(MxParser.FunctionExprContext ctx) { return visitChildren(ctx); }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.</p>
   */
  @Override
  public BaseNode 
  visitMemberExpr(MxParser.MemberExprContext ctx) { return visitChildren(ctx); }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.</p>
   */
  @Override
  public BaseNode 
  visitBinaryExpr(MxParser.BinaryExprContext ctx) { return visitChildren(ctx); }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.</p>
   */
  @Override
  public BaseNode 
  visitBracketExpr(MxParser.BracketExprContext ctx) { return visitChildren(ctx); }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.</p>
   */
  @Override
  public BaseNode 
  visitFormattedString(MxParser.FormattedStringContext ctx) { return visitChildren(ctx); }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.</p>
   */
  @Override
  public BaseNode 
  visitParentExpr(MxParser.ParentExprContext ctx) { return visitChildren(ctx); }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.</p>
   */
  @Override
  public BaseNode 
  visitAtomExpr(MxParser.AtomExprContext ctx) { return visitChildren(ctx); }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.</p>
   */
  @Override
  public BaseNode 
  visitUnaryExpr(MxParser.UnaryExprContext ctx) { return visitChildren(ctx); }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.</p>
   */
  @Override
  public BaseNode 
  visitNewType(MxParser.NewTypeContext ctx) { return visitChildren(ctx); }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.</p>
   */
  @Override
  public BaseNode 
  visitTernaryExpr(MxParser.TernaryExprContext ctx) { return visitChildren(ctx); }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.</p>
   */
  @Override
  public BaseNode 
  visitAssignExpr(MxParser.AssignExprContext ctx) { return visitChildren(ctx); }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.</p>
   */
  @Override
  public BaseNode 
  visitThis(MxParser.ThisContext ctx) { return visitChildren(ctx); }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.</p>
   */
  @Override
  public BaseNode 
  visitNull(MxParser.NullContext ctx) { return visitChildren(ctx); }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.</p>
   */
  @Override
  public BaseNode 
  visitTF(MxParser.TFContext ctx) { return visitChildren(ctx); }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.</p>
   */
  @Override
  public BaseNode 
  visitIdentifier(MxParser.IdentifierContext ctx) { return visitChildren(ctx); }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.</p>
   */
  @Override
  public BaseNode 
  visitDecimal(MxParser.DecimalContext ctx) { return visitChildren(ctx); }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.</p>
   */
  @Override
  public BaseNode 
  visitStringConst(MxParser.StringConstContext ctx) { return visitChildren(ctx); }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.</p>
   */
  @Override
  public BaseNode 
  visitLiteralMultiList(MxParser.LiteralMultiListContext ctx) { return visitChildren(ctx); }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.</p>
   */
  @Override
  public BaseNode 
  visitLiteralList(MxParser.LiteralListContext ctx) { return visitChildren(ctx); }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.</p>
   */
  @Override
  public BaseNode 
  visitLiteralAtom(MxParser.LiteralAtomContext ctx) { return visitChildren(ctx); }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.</p>
   */
  @Override
  public BaseNode 
  visitFString(MxParser.FStringContext ctx) { return visitChildren(ctx); }

}
