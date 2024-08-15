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
import util.error.TypeMismatch;
import util.info.ClassInfo;
import util.info.FuncInfo;
import util.info.TypeInfo;
import util.info.VarInfo;

import java.util.ArrayList;
import java.util.Objects;

public class AstBuilder extends MxBaseVisitor<BaseNode> {
  @Override
  public BaseNode visitProgram(MxParser.ProgramContext ctx) {
    var program = new Program();
    program.pos = new Position(ctx.start);
    program.defs = new ArrayList<>();
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
    block.statements = new ArrayList<>();
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
    typeNode.width = new ArrayList<>();
    boolean flag = false;
    for(var br : ctx.bracket()) {
      if(br.expression() != null) {
        if(flag) throw new SyntaxError
                ("Illegal initialization in typename", typeNode.pos);
        else {
          typeNode.width.add((Expr) visit(br.expression()));
        }
      }
      else {
        flag = true;
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
    // varDef.info = varDef.type.info;
    varDef.list = new ArrayList<>();
    if(!varDef.type.width.isEmpty()) {
      throw new SyntaxError("Variable definition illegal", varDef.pos);
    }
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
    var funcDef = new FuncDef();
    funcDef.pos = new Position(ctx.start);
    funcDef.name = ctx.Identifier().getText();
    funcDef.retType = (TypeNode) visit(ctx.typeName());
    funcDef.body = (Block) visit(ctx.block());
    funcDef.params = new ArrayList<>();
    if(ctx.parameterList() != null) {
      int i = 0;
      for (var v : ctx.parameterList().typeName()) {
        var p = new Pair<>((TypeNode) visit(v),
                ctx.parameterList().Identifier(i).getText());
        funcDef.params.add(p);
        ++i;
      }
    }
    funcDef.info = new FuncInfo(funcDef.name, funcDef.retType.info);
    return funcDef;
  }
  @Override
  public BaseNode 
  visitClassDef(MxParser.ClassDefContext ctx) {
    var classDef = new ClassDef();
    classDef.pos = new Position(ctx.start);
    classDef.name = ctx.Identifier().getText();
    var con = new FuncDef();
    con.retType = new TypeNode();
    if(ctx.constructor().isEmpty()) {
      con.retType.width = null;
      con.retType.info = new TypeInfo("void", 0);
      con.name = classDef.name;
      con.body = new Block();
      // Seems buggy here
    }
    else if(ctx.constructor().size() == 1) {
      con.retType.width = null;
      con.retType.info = new TypeInfo("void", 0);
      con.name = ctx.constructor(0).Identifier().getText();
      if(!Objects.equals(classDef.name, con.name)) {
        throw new SyntaxError("Illegal constructor", classDef.pos);
      }
      con.body = (Block) visit(ctx.constructor(0).block());
    }
    else throw new SyntaxError("Illegal constructor", classDef.pos);
    classDef.constructor = con;
    classDef.vars = new ArrayList<>();
    classDef.funcs = new ArrayList<>();
    classDef.info = new ClassInfo(classDef.name);
    for(var v : ctx.varDef()) {
      var varDef = (VarDef) visit(v);
      classDef.vars.add(varDef);
      // put into info at semantic check ?
    }
    for(var v : ctx.funcDef()) {
      var funcDef = (FuncDef) visit(v);
      classDef.funcs.add(funcDef);
      classDef.info.funcs.put(funcDef.name, funcDef.info);
    }
    return classDef;
  }
  /* visitArgumentList */
  @Override
  public BaseNode 
  visitSelfExpr(MxParser.SelfExprContext ctx) {
    var selfExpr = new SelfExpr();
    selfExpr.pos = new Position(ctx.start);
    selfExpr.object = (Expr) visit(ctx.expression());
    selfExpr.op = ctx.op.getText();
    return selfExpr;
  }
  @Override
  public BaseNode 
  visitFunctionExpr(MxParser.FunctionExprContext ctx) {
    var func = new FuncExpr();
    func.pos = new Position(ctx.start);
    func.func = (Expr) visit(ctx.expression());
    func.args = new ArrayList<>();
    if(ctx.argumentList() != null) {
      for(var v : ctx.argumentList().expression()) {
        func.args.add((Expr) visit(v));
      }
    }
    return func;
  }
  @Override
  public BaseNode 
  visitMemberExpr(MxParser.MemberExprContext ctx) {
    var memberExpr = new MemberExpr();
    memberExpr.pos = new Position(ctx.start);
    memberExpr.expr = (Expr) visit(ctx.expression());
    memberExpr.member = ctx.Identifier().getText();
    return memberExpr;
  }
  @Override
  public BaseNode 
  visitBinaryExpr(MxParser.BinaryExprContext ctx) {
    var binary = new BinaryExpr();
    binary.pos = new Position(ctx.start);
    binary.lhs = (Expr) visit(ctx.expression(0));
    binary.rhs = (Expr) visit(ctx.expression(1));
    binary.op = ctx.op.getText();
    return binary;
  }
  @Override
  public BaseNode 
  visitBracketExpr(MxParser.BracketExprContext ctx) {
    var bracket = new BracketExpr();
    bracket.pos = new Position(ctx.start);
    bracket.array = (Expr) visit(ctx.expression(0));
    bracket.index = (Expr) visit(ctx.expression(1));
    return bracket;
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
  @Override
  public BaseNode 
  visitUnaryExpr(MxParser.UnaryExprContext ctx) {
    var unaryExpr = new UnaryExpr();
    unaryExpr.pos = new Position(ctx.start);
    unaryExpr.op = ctx.op.getText();
    unaryExpr.object = (Expr) visit(ctx.expression());
    return unaryExpr;
  }
  @Override
  public BaseNode 
  visitNewType(MxParser.NewTypeContext ctx) {
    var newExpr = new NewExpr();
    newExpr.pos = new Position(ctx.start);
    newExpr.type = (TypeNode) visit(ctx.typeName());
    newExpr.init = ctx.literalMultiList() != null ?
            (LiteralML) visit(ctx.literalMultiList()) : null;
    return newExpr;
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
    var literalML = new LiteralML();
    literalML.pos = new Position(ctx.start);
    if(ctx.literalList() != null) {
      literalML.atomList = new ArrayList<>();
      if(ctx.literalList().literalAtom().isEmpty()) {
        // {}
        literalML.dimension = -1;
        literalML.type = LiteralML.Type.ANY;
        return literalML;
      }
      else {
        // {1, 2}
        literalML.dimension = 1;
        if(ctx.literalList().literalAtom(0).Decimal() != null) {
          literalML.type = LiteralML.Type.DEC;
          for(var v : ctx.literalList().literalAtom()) {
            if(v.Decimal() == null) {
              throw new TypeMismatch("Wrong type in array const", literalML.pos);
            }
            literalML.atomList.add(v.getText());
          }
        }
        else if(ctx.literalList().literalAtom(0).StringConst() != null) {
          literalML.type = LiteralML.Type.STR;
          for(var v : ctx.literalList().literalAtom()) {
            if(v.StringConst() == null) {
              throw new TypeMismatch("Wrong type in array const", literalML.pos);
            }
            literalML.atomList.add(v.getText());
          }
        }
        else if(ctx.literalList().literalAtom(0).Null() != null) {
          literalML.type = LiteralML.Type.NULL;
          for(var v : ctx.literalList().literalAtom()) {
            if(v.Null() == null) {
              throw new TypeMismatch("Wrong type in array const", literalML.pos);
            }
            literalML.atomList.add(v.getText());
          }
        }
        else {
          literalML.type = LiteralML.Type.TF;
          for(var v : ctx.literalList().literalAtom()) {
            if(v.True() == null && v.False() == null) {
              throw new TypeMismatch("Wrong type in array const", literalML.pos);
            }
            literalML.atomList.add(v.getText());
          }
        }
      }
    }
    else {
      literalML.list = new ArrayList<>();
      literalML.type = LiteralML.Type.UNKNOWN;
      for(var v : ctx.literalMultiList()) {
        literalML.list.add((LiteralML) visit(v));
      }
    }
    return literalML;
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
