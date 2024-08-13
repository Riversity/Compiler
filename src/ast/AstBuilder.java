package ast;

import org.antlr.v4.runtime.misc.Pair;

import ast.node.*;
import ast.node.def.*;
import ast.node.expr.*;
import ast.node.stmt.*;
import parser.MxBaseVisitor;
import parser.MxParser;
import util.Position;
import util.error.SyntaxError;
import util.info.TypeInfo;

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
    var typeNode = new TypeNode();
    typeNode.pos = new Position(ctx.start);
    String type = ctx.type.getText();
    typeNode.info = new TypeInfo(type, ctx.bracket().size());
    boolean flag = false;
    for(var br : ctx.bracket()) {
      if(br.expression() != null) {
        if(flag) throw new SyntaxError
                ("Illegal initialization", typeNode.pos);
        else {
          flag = true;
          typeNode.width.add((Expr) visit(br.expression()));
        }
      }
    }
    return typeNode;
  }
  /* visitBracket */
  @Override
  public BaseNode 
  visitVarDef(MxParser.VarDefContext ctx) {
    var varDef = new VarDef();
    varDef.pos = new Position(ctx.start);
    varDef.type = (TypeNode) visit(ctx.typeName());
    for(var term : ctx.varTerm()) {
      var id =  term.Identifier().getText();
      Expr expr = null;
      if(term.literalMultiList() != null) {
        expr = (LiteralML) visit(term.literalMultiList());
      }
      else if(term.expression() != null) {
        expr = (Expr) visit(term.expression());
      }
      varDef.list.add(new Pair<>(id, expr));
    }
    return varDef;
  }
  /* visitVarTerm */
  @Override
  public BaseNode 
  visitFuncDef(MxParser.FuncDefContext ctx) {

  }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.</p>
   */
  @Override
  public BaseNode 
  visitParameterList(MxParser.ParameterListContext ctx) {

  }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.</p>
   */
  @Override
  public BaseNode 
  visitClassDef(MxParser.ClassDefContext ctx) {

  }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling
   * {@link #visitChildren} on {@code ctx}.</p>
   */
  @Override
  public BaseNode 
  visitArgumentList(MxParser.ArgumentListContext ctx) {

  }
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
  visitBracketExpr(MxParser.BracketExprContext ctx) {

  }
  @Override
  public BaseNode 
  visitFormattedString(MxParser.FormattedStringContext ctx) {
    return visit(ctx.fString());
  }
  @Override
  public BaseNode 
  visitParentExpr(MxParser.ParentExprContext ctx) {
    return visit(ctx.expression());
  }
  @Override
  public BaseNode 
  visitAtomExpr(MxParser.AtomExprContext ctx) {
    var atom = new AtomExpr();
    atom.pos = new Position(ctx.start);
    atom.val = ctx.getText();
    if(ctx.This() != null) atom.atomType = AtomExpr.Type.THIS;
    else if(ctx.Null() != null) atom.atomType = AtomExpr.Type.NULL;
    else if(ctx.True() != null || ctx.False() != null) atom.atomType = AtomExpr.Type.TF;
    else if(ctx.Identifier() != null) atom.atomType = AtomExpr.Type.ID;
    else if(ctx.Decimal() != null) atom.atomType = AtomExpr.Type.DEC;
    else if(ctx.StringConst() != null) atom.atomType = AtomExpr.Type.STR;
    return atom;
  }
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
  visitNewType(MxParser.NewTypeContext ctx) {

  }
  @Override
  public BaseNode 
  visitTernaryExpr(MxParser.TernaryExprContext ctx) {
    var ternary = new TernaryExpr();
    ternary.pos = new Position(ctx.start);
    ternary.condition = (Expr) visit(ctx.expression(0));
    ternary.trueBranch = (Expr) visit(ctx.expression(1));
    ternary.falseBranch = (Expr) visit(ctx.expression(2));
    return ternary;
  }
  @Override
  public BaseNode 
  visitAssignExpr(MxParser.AssignExprContext ctx) {
    var assign = new AssignExpr();
    assign.pos = new Position(ctx.start);
    assign.lhs = (Expr) visit(ctx.expression(0));
    assign.rhs = (Expr) visit(ctx.expression(1));
    return assign;
  }
  @Override
  public BaseNode 
  visitLiteralMultiList(MxParser.LiteralMultiListContext ctx) {

  }
  /* literalList */
  /* literalAtom */
  @Override
  public BaseNode 
  visitFString(MxParser.FStringContext ctx) {
    var fStr = new FStrExpr();
    fStr.pos = new Position(ctx.start);
    /* do not trim f" $ etc */
    if(ctx.FAtom() != null) {
      fStr.fAtom = ctx.FAtom().getText();
    }
    else {
      fStr.fHead = ctx.FHead().getText();
      fStr.fTail = ctx.FTail().getText();
      for (var body : ctx.FBody()) {
        fStr.fBody.add(body.getText());
      }
    }
    return fStr;
  }
}
