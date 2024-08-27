package ir;

import ir.info.IRVarInfo;
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

import java.util.ArrayList;
import java.util.HashMap;

import static ir.util.IRUtil.rename;

public class IRBuilder implements AstVisitor<IRNode> {
  GlobalScope globalScope;
  BaseScope curScope;
  String curClassName = null;

  public IRNode visit(BaseNode node) throws MyError {
    throw new InternalError("IRBuilder ASTNode type invalid", node.pos);
  }
  public IRNode visit(Program node) throws MyError {
    globalScope = node.scope;
    curScope = node.scope;

    var init = new IRFuncDef();
    init.name = "__init";
    init.params = new ArrayList<>();
    init.blocks = new ArrayList<>();
    init.blocks.add(new IRBlock());
    init.returnType = new IRType("void");

    var ret = new IRRoot();
    ret.funcs = new ArrayList<>();
    ret.funcs.add(init);
    ret.defs = new ArrayList<>();
    for(var def : node.defs) {
      if(def instanceof ClassDef) {
        var structMember = new ArrayList<IRType>();
        for (var v : ((ClassDef) def).vars) {
          for (var k : v.list) {
            structMember.add(new IRType(v.type.info));
          }
        }
        var struct = new IRStructType("%class." + ((ClassDef) def).name, structMember);
        ret.defs.add(new IRGlobDef(new IRVarInfo(struct, "%class." + ((ClassDef) def).name)));
      }
    }

    for(var def : node.defs) {
      if(def instanceof VarDef) {
        for(var d : ((VarDef) def).list) {
          ret.defs.add(new IRGlobDef(new IRVarInfo
                  (new IRType(((VarDef) def).type.info), rename(d.a, curScope))));
          if(d.b != null) {
            var inst = new IRStore();
            inst.dest = new IRVarInfo(new IRType(((VarDef) def).type.info), rename(d.a, curScope));
            var exprRes = (IRStmt) d.b.accept(this);
            inst.src = exprRes.dest;
            init.blocks.get(0).insts.addAll(exprRes.insts);
            init.blocks.get(0).insts.add(inst);
          }
        }
      }
      if(def instanceof ClassDef) {
        curClassName = ((ClassDef) def).name;
        for(var func : ((ClassDef) def).funcs) {
          ret.funcs.add((IRFuncDef) func.accept(this));
        }
        curClassName = null;
      }
    }

    for(var def : node.defs) {
      if(def instanceof FuncDef) {
        var func = def.accept(this);
        if(((IRFuncDef) func).name.equals("main")) {
          var inst = new IRCall();
          inst.funcName = "__init";
          inst.args = new ArrayList<>();
          inst.type = new IRType("void");
          ((IRFuncDef) func).blocks.get(0).insts.add(0, inst);
        }
        ret.funcs.add((IRFuncDef) func);
      }
    }

    init.blocks.get(0).label = "init";
    init.blocks.get(0).exit = new IRReturn(new IRType("void"), null);
    return ret;
  }

  public IRNode visit(FuncDef node) throws MyError {

  }
  public IRNode visit(ClassDef node) throws MyError {


    return null;
  }
  public IRNode visit(VarDef node) throws MyError {
    var ret = new ManyNode();
    ret.many = new ArrayList<>();
    // TODO
    return ret;
  }

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
