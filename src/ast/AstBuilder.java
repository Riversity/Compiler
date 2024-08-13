package ast;

import ast.node.*;
import ast.node.def.*;
import ast.node.expr.*;
import ast.node.stmt.*;
import parser.MxBaseVisitor;
import parser.MxParser;
import util.Position;

public class AstBuilder extends MxBaseVisitor<BaseNode> {
  @Override
  public BaseNode visitProgram(MxParser.ProgramContext ctx) {
    var program = new Program();
    program.pos = new Position(ctx.start);
    // caution scope
    for(var def : ctx.children) {
      if(def instanceof MxParser.VarDefContext ||
         def instanceof MxParser.FuncDefContext ||
         def instanceof MxParser.ClassDefContext)
        program.defs.add((Def) visit(def));
    }
    return program;
  }
  @Override
  public BaseNode 
  visitBlock(MxParser.BlockContext ctx) {
    var block = new Block();
    block.pos = new Position(ctx.start);
    for(var stmt : ctx.children) {
      block.statements.add((Stmt) visit(stmt));
    }
    return block;
  }
  @Override
  public BaseNode 
  visitBlockStmt(MxParser.BlockStmtContext ctx) {
    return visit(ctx.block());
  }
  @Override
  public BaseNode 
  visitVarDefStmt(MxParser.VarDefStmtContext ctx) {
    var varDefStmt = new VarDefStmt();
    varDefStmt.pos = new Position(ctx.start);
    varDefStmt.def = (VarDef) visit(ctx.varDef());
    return varDefStmt;
  }
  @Override
  public BaseNode 
  visitIfElseStmt(MxParser.IfElseStmtContext ctx) {
    var ifElseStmt = new IfElseStmt();
    ifElseStmt.pos = new Position(ctx.start);
    ifElseStmt.condition = (Expr) visit(ctx.expression());
    ifElseStmt.trueBranch = (Stmt) visit(ctx.statement(0));
    if(ctx.statement().size() == 2) {
      ifElseStmt.falseBranch = (Stmt) visit(ctx.statement(1));
    }
    else {
      ifElseStmt.falseBranch = null;
    }
    return ifElseStmt;
  }
  @Override
  public BaseNode 
  visitBreakStmt(MxParser.BreakStmtContext ctx) {
    var breakStmt = new BreakStmt();
    breakStmt.pos = new Position(ctx.start);
    return breakStmt;
  }
  @Override
  public BaseNode 
  visitContinueStmt(MxParser.ContinueStmtContext ctx) {
    var continueStmt = new ContinueStmt();
    continueStmt.pos = new Position(ctx.start);
    return continueStmt;
  }
  @Override
  public BaseNode 
  visitReturnStmt(MxParser.ReturnStmtContext ctx) {
    var ret = new RetStmt();
    ret.pos = new Position(ctx.start);
    ret.retVal = ctx.expression() != null ?
            (Expr) visit(ctx.expression()) : null;
    return ret;
  }
  @Override
  public BaseNode 
  visitWhileStmt(MxParser.WhileStmtContext ctx) {
    var whileStmt = new WhileStmt();
    whileStmt.pos = new Position(ctx.start);
    whileStmt.condition = (Expr) visit(ctx.expression());
    whileStmt.body = (Stmt) visit(ctx.statement());
    return whileStmt;
  }
  @Override
  public BaseNode 
  visitForStmt(MxParser.ForStmtContext ctx) {
    var forStmt = new ForStmt();
    forStmt.pos = new Position(ctx.start);
    forStmt.initStmt = ctx.varDef() != null ?
            (VarDef) visit(ctx.varDef()) : null;
    forStmt.initExpr = ctx.initialExpr != null ?
            (Expr) visit(ctx.initialExpr) :null;
    forStmt.condition = ctx.condiExpr != null ?
            (Expr) visit(ctx.condiExpr) :null;
    forStmt.step = ctx.stepExpr != null ?
            (Expr) visit(ctx.stepExpr) :null;
    forStmt.body = (Stmt) visit(ctx.statement());
    return forStmt;
  }
  @Override
  public BaseNode 
  visitPurExprStmt(MxParser.PurExprStmtContext ctx) {
    var purExprStmt = new PurExprStmt();
    purExprStmt.pos = new Position(ctx.start);
    purExprStmt.expr = (Expr) visit(ctx.expression());
    return purExprStmt;
  }
  @Override
  public BaseNode 
  visitEmptyStmt(MxParser.EmptyStmtContext ctx) {
    var emptyStmt = new EmptyStmt();
    emptyStmt.pos = new Position(ctx.start);
    return emptyStmt;
  }
  @Override
  public BaseNode 
  visitTypeName(MxParser.TypeNameContext ctx) {
    // Not Finished!!!

  }
  /* visitBracket */
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
