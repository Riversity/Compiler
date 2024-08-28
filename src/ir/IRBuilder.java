package ir;

import ir.info.IRBaseInfo;
import ir.info.IRConstInfo;
import ir.info.IRFuncInfo;
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
import sema.util.info.*;
import sema.util.scope.*;

import java.util.ArrayList;
import java.util.HashMap;

import static ir.util.IRNative.*;
import static ir.util.IRUtil.*;

public class IRBuilder implements AstVisitor<IRNode> {
  ArrayList<IRGlobDef> strDefs = new ArrayList<>();
  String curClassName = null;
  IRFuncDef cur;

  public IRNode visit(BaseNode node) throws MyError {
    throw new InternalError("IRBuilder ASTNode type invalid", node.pos);
  }
  public IRNode visit(Program node) throws MyError {
    var init = new IRFuncDef();
    init.name = "__init";
    init.params = new ArrayList<>();
    init.blocks = new ArrayList<>();
    init.blocks.add(new IRBlock());
    init.returnType = irVoidType;

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
                  (new IRType(((VarDef) def).type.info), rename(d.a, node.scope))));
          if(d.b != null) {
            cur = init;
            var inst = new IRStore();
            inst.dest = new IRVarInfo(new IRType(((VarDef) def).type.info), rename(d.a, node.scope));
            var exprRes = (IRStmt) d.b.accept(this);
            inst.src = exprRes.dest;
            cur.addInst(inst);
            cur = null;
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
          inst.type = irVoidType;
          if(((IRFuncDef) func).blocks.isEmpty()) {
            ((IRFuncDef) func).addInst(inst);
          }
          else {
            ((IRFuncDef) func).blocks.get(0).insts.add(0, inst);
          }
        }
        ret.funcs.add((IRFuncDef) func);
      }
    }

    init.blocks.get(0).label = "init";
    init.blocks.get(0).exit = new IRReturn(new IRType("void"), null);

    ret.defs.addAll(strDefs);

    return ret;
  }

  public IRNode visit(FuncDef node) throws MyError {
    var ret = new IRFuncDef();
    ret.blocks = new ArrayList<>();
    ret.params = new ArrayList<>();
    cur = ret;
    ret.returnType = new IRType(node.retType.info);
    ret.name = curClassName != null ? "__" + curClassName + "." + node.name : node.name;
    for(var p : node.params) {
      if(curClassName != null) ret.params.add(new IRVarInfo(irPtrType, "%this"));
      ret.params.add(new IRVarInfo(new IRType(p.a.info), rename(p.b, node.scope)));
    }
    for(var s : node.body.statements) {
      s.accept(this);
    }
    cur = null;
    return ret;
  }
  public IRNode visit(ClassDef node) throws MyError {
    // TODO
    return null;
  }
  public IRNode visit(VarDef node) throws MyError {
    var ret = new ManyNode();
    ret.many = new ArrayList<>();
    // TODO
    return ret;
  }

  public IRNode visit(AtomExpr node) throws MyError {
    var ret = new IRStmt();
    switch(node.atomType) {
      case TF:
        ret.dest = new IRConstInfo(irBoolType, node.val.equals("true") ? "1" : "0");
        break;
      case NULL:
        ret.dest = new IRConstInfo(irPtrType, "0");
        break;
      case DEC:
        ret.dest = new IRConstInfo(irIntType, node.val);
        break;
      case ID:
        if(node.info instanceof FuncInfo) {
          String tmpName;
          if(curClassName != null) {
            tmpName = "__" + curClassName + "." + node.val;
          }
          else tmpName = node.val;
          ret.dest = new IRFuncInfo(tmpName, new IRType(((FuncInfo) node.info).type));
        }
        else {
          String tmpName;
          if(node.whereToFind instanceof ClassScope) {
            tmpName = "__" + curClassName + "." + node.val;
          }
          else tmpName = rename(node.val, node.whereToFind);
          var type = new IRType((TypeInfo) node.info);
          var dest = new IRVarInfo(type, getTmpVar());
          var src = new IRVarInfo(irPtrType, tmpName);
          cur.addInst(new IRLoad(dest, src));
          ret.dest = dest;
          ret.destAddr = src;
        }
      case STR:
        var strDest = new IRVarInfo(irPtrType, getStrVar());
        var str = stringConstConvert(node.val);
        strDefs.add(new IRStrDef(strDest, str));
        ret.dest = strDest;
      case THIS:
        var loadSrc = new IRVarInfo(irPtrType, "%this");
        var loadDest = new IRVarInfo(irPtrType, getTmpVar());
        cur.addInst(new IRLoad(loadDest, loadSrc));
        ret.dest = loadDest;
      default:
        throw new InternalError("IRBuilder AtomExpr failed", node.pos);
    }
    return ret;
  }
  public IRNode visit(FStrExpr node) throws MyError {
    // TODO
    return null;
  }
  public IRNode visit(MemberExpr node) throws MyError {
    // TODO
    return null;
  }
  public IRNode visit(BracketExpr node) throws MyError {
    // TODO
    return null;
  }
  public IRNode visit(FuncExpr node) throws MyError {
    // TODO
    return null;
  }
  public IRNode visit(SelfExpr node) throws MyError {
    // TODO
    return null;
  }
  public IRNode visit(UnaryExpr node) throws MyError {
    // TODO
    return null;
  }
  public IRNode visit(NewExpr node) throws MyError {
    // TODO
    return null;
  }
  public IRNode visit(BinaryExpr node) throws MyError {
    // TODO
    return null;
  }
  public IRNode visit(LiteralML node) throws MyError {
    // TODO
    return null;
  }
  public IRNode visit(AssignExpr node) throws MyError {
    // TODO
    return null;
  }
  public IRNode visit(TernaryExpr node) throws MyError {
    // TODO
    return null;
  }

  public IRNode visit(Block node) throws MyError {
    // TODO
    return null;
  }
  public IRNode visit(VarDefStmt node) throws MyError {
    // TODO
    return null;
  }
  public IRNode visit(IfElseStmt node) throws MyError {
    // TODO
    return null;
  }
  public IRNode visit(BreakStmt node) throws MyError {
    // TODO
    return null;
  }
  public IRNode visit(ContinueStmt node) throws MyError {
    // TODO
    return null;
  }
  public IRNode visit(RetStmt node) throws MyError {
    // TODO
    return null;
  }
  public IRNode visit(WhileStmt node) throws MyError {
    // TODO
    return null;
  }
  public IRNode visit(ForStmt node) throws MyError {
    // TODO
    return null;
  }
  public IRNode visit(PurExprStmt node) throws MyError {
    // TODO
    return null;
  }
  public IRNode visit(EmptyStmt node) throws MyError {
    // TODO
    return null;
  }
}
