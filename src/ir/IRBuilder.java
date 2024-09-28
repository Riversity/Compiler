package ir;

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
import sema.ast.node.TypeNode;
import sema.ast.node.def.*;
import sema.ast.node.expr.*;
import sema.ast.node.stmt.*;
import sema.util.error.InternalError;
import sema.util.error.MyError;
import sema.util.info.*;
import sema.util.scope.*;

import java.util.ArrayList;
import java.util.Stack;

import static ir.util.IRNative.*;
import static ir.util.IRUtil.*;
import static sema.util.Native.*;

public class IRBuilder implements AstVisitor<IRNode> {
  ArrayList<IRGlobDef> strDefs = new ArrayList<>();
  String curClassName = null;
  IRFuncDef curFunc;
  IRBlock cur;
  Stack<IRBlock> breakDests = new Stack<>();
  Stack<IRBlock> continueDests = new Stack<>();

  public IRBlock addBr(IRBaseInst br) {
    IRBlock ret = new IRBlock(getBlockLabel(), cur.exit);
    ret.unreachable = true;
    curFunc.blocks.add(ret);
    cur.exit = br;
    cur = ret;
    return ret;
  }

  public IRNode visit(BaseNode node) throws MyError {
    throw new InternalError("IRBuilder ASTNode type invalid", node.pos);
  }
  public IRNode visit(Program node) throws MyError {
    var init = new IRFuncDef();
    init.name = "__init";
    init.params = new ArrayList<>();
    init.blocks = new ArrayList<>();
    init.blocks.add(new IRBlock("init", new IRReturn(irVoidType, null)));
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

    curFunc = init;
    cur = init.blocks.get(0);
    for(var def : node.defs) {
      if (def instanceof VarDef) {
        for (var d : ((VarDef) def).list) {
          ret.defs.add(new IRGlobDef(new IRVarInfo
                  (new IRType(((VarDef) def).type.info), rename(d.a, node.scope))));
          if (d.b != null) {
            var inst = new IRStore();
            inst.dest = new IRVarInfo(new IRType(((VarDef) def).type.info), rename(d.a, node.scope));
            var exprRes = (IRExpr) d.b.accept(this);
            inst.src = exprRes.dest;
            cur.addInst(inst);
          }
        }
      }
    }
    cur = null;
    curFunc = null;

    for(var def : node.defs) {
      if(def instanceof ClassDef) {
        curClassName = ((ClassDef) def).name;
        for(var func : ((ClassDef) def).funcs) {
          ret.funcs.add((IRFuncDef) func.accept(this));
        }

        var contNode = new FuncDef();
        contNode.name = "__ctor";
        contNode.params = new ArrayList<>();
        contNode.body = ((ClassDef) def).constructor;
        contNode.retType = new TypeNode();
        contNode.retType.width = new ArrayList<>();
        contNode.retType.info = new TypeInfo("void", 0);
        IRFuncDef cont = (IRFuncDef) contNode.accept(this);
        ret.funcs.add(cont);
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
          ((IRFuncDef) func).blocks.get(0).insts.add(0, inst);
        }
        ret.funcs.add((IRFuncDef) func);
      }
    }

    ret.defs.addAll(strDefs);

    return ret;
  }

  public IRNode visit(FuncDef node) throws MyError {
    var ret = new IRFuncDef();
    ret.blocks = new ArrayList<>();
    ret.params = new ArrayList<>();
    curFunc = ret;
    ret.returnType = new IRType(node.retType.info);
    ret.name = curClassName != null ? "__" + curClassName + "." + node.name : node.name;
    if(curClassName != null) ret.params.add(new IRVarInfo(irPtrType, "%this"));
    for(var p : node.params) {
      ret.params.add(new IRVarInfo(new IRType(p.a.info), rename(p.b, node.scope)));
    }
    String name1 = getBlockLabel();
    String name2 = getBlockLabel();
    IRBlock curBlock = new IRBlock(name1, new IRJump());
    cur = curBlock;
    IRBlock nextBlock = new IRBlock(name2, new IRReturn(ret.returnType, new IRConstInfo(ret.returnType, "0")));
    ((IRJump) curBlock.exit).end = nextBlock;
    ret.blocks.add(curBlock);
    ret.blocks.add(nextBlock);
    for(var s : node.body.statements) {
      s.accept(this);
    }
    cur = null;
    curFunc = null;
    return ret;
  }
  public IRNode visit(ClassDef node) throws MyError {
    throw new InternalError("ClassDef should not be visited", node.pos);
  }
  public IRNode visit(VarDef node) throws MyError {
    for(var d : node.list) {
      var alloc = new IRAlloca();
      alloc.type = new IRType(node.type.info);
      alloc.dest = new IRVarInfo(alloc.type, rename(d.a, node.scope));
      cur.addInst(alloc);

      if(d.b != null) {
        var inst = new IRStore();
        inst.dest = alloc.dest;
        var exprRes = (IRExpr) d.b.accept(this);
        inst.src = exprRes.dest;
        cur.addInst(inst);
      }
    }
    return null;
  }

  public IRNode visit(AtomExpr node) throws MyError {
    var ret = new IRExpr();
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
          var type = new IRType((TypeInfo) node.info);
          var dest = new IRVarInfo(type, getTmpVar());
          if(node.whereToFind instanceof ClassScope) {
            // inside ClassDef
            tmpName = "%__" + curClassName + "." + node.val;
            var getele = new IRGetElementPtr();
            int offset = ((ClassInfo) node.whereToFind.info).offset.get(node.val);
            getele.isMember = true;
            getele.index = new IRConstInfo(irIntType, Integer.toString(offset));
            getele.type = new IRType("%class." + curClassName);
            getele.src = new IRVarInfo(irPtrType, "%this");
            var src = new IRVarInfo(irPtrType, tmpName);
            getele.dest = src;
            cur.addInst(getele);
            cur.addInst(new IRLoad(dest, src));
            ret.dest = dest;
            ret.destAddr = src;
          }
          else {
            tmpName = rename(node.val, node.whereToFind);
            var src = new IRVarInfo(irPtrType, tmpName);
            cur.addInst(new IRLoad(dest, src));
            ret.dest = dest;
            ret.destAddr = src;
          }
        }
        break;
      case STR:
        var strDest = new IRVarInfo(irPtrType, getStrVar());
        var str = stringConstConvert(node.val);
        strDefs.add(new IRStrDef(strDest, str));
        ret.dest = strDest;
        break;
      case THIS:
        var loadSrc = new IRVarInfo(irPtrType, "%this");
        var loadDest = new IRVarInfo(irPtrType, getTmpVar());
        cur.addInst(new IRLoad(loadDest, loadSrc));
        ret.dest = loadDest;
        break;
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
    var ret = new IRExpr();
    var call = new IRCall();
    call.type = new IRType((TypeInfo) node.info);
    String curCallClass = null;
    if(node.func instanceof MemberExpr) {
      MemberExpr tmp = (MemberExpr) node.func;
      curCallClass = tmp.expr.info.name;
    }
    call.funcName = curCallClass != null ? "__" + curCallClass + "." + node.func.info.name : node.func.info.name;
    if(!call.type.equals(irVoidType)) call.dest = new IRVarInfo(call.type, getTmpVar());
    call.args = new ArrayList<>();
    for(var a : node.args) {
      IRExpr tmpRes = (IRExpr) a.accept(this);
      call.args.add(tmpRes.dest);
    }
    cur.addInst(call);
    ret.dest = call.dest;
    return ret;
  }

  public IRNode visit(SelfExpr node) throws MyError {
    var ret = new IRExpr();
    var obj = (IRExpr) node.object.accept(this);
    // RValue

    var add = new IRArith();
    add.dest = new IRVarInfo(irIntType, getTmpVar());
    add.lhs = obj.dest;
    add.rhs = new IRConstInfo(irIntType, "1");
    add.op = node.op.equals("++") ? "add" : "sub";
    var store = new IRStore();
    store.src = add.dest;
    store.dest = (IRVarInfo) obj.destAddr;
    cur.addInst(add);
    cur.addInst(store);

    ret.dest = obj.dest;
    return ret;
  }
  public IRNode visit(UnaryExpr node) throws MyError {
    var ret = new IRExpr();
    var obj = (IRExpr) node.object.accept(this);
    if(node.op.equals("++") || node.op.equals("--")) {
      // LValue
      var arith = new IRArith();
      arith.dest = new IRVarInfo(irIntType, getTmpVar());
      arith.lhs = obj.dest;
      arith.rhs = new IRConstInfo(irIntType, "1");
      arith.op = node.op.equals("++") ? "add" : "sub";
      var store = new IRStore();
      store.src = arith.dest;
      store.dest = (IRVarInfo) obj.destAddr;
      cur.addInst(arith);
      cur.addInst(store);

      ret.dest = arith.dest;
      ret.destAddr = obj.destAddr;
    }
    else if(node.op.equals("+")) {
      ret.dest = obj.dest;
    }
    else if(node.op.equals("-")) {
      var arith = new IRArith();
      arith.dest = new IRVarInfo(irIntType, getTmpVar());
      arith.lhs = irZero;
      arith.rhs = obj.dest;
      arith.op = "sub";
      cur.addInst(arith);

      ret.dest = arith.dest;
    }
    else if(node.op.equals("!")) {
      var arith = new IRArith();
      arith.dest = new IRVarInfo(irBoolType, getTmpVar());
      arith.lhs = obj.dest;
      arith.rhs = new IRConstInfo(irBoolType, "1");
      arith.op = "xor";
      cur.addInst(arith);

      ret.dest = arith.dest;
    }
    else if(node.op.equals("~")) {
      var arith = new IRArith();
      arith.dest = new IRVarInfo(irIntType, getTmpVar());
      arith.lhs = obj.dest;
      arith.rhs = new IRConstInfo(irIntType, "-1");
      arith.op = "xor";
      cur.addInst(arith);

      ret.dest = arith.dest;
    }
    return ret;
  }

  public IRNode visit(NewExpr node) throws MyError {
    // TODO
    return null;
  }
  public IRNode visit(BinaryExpr node) throws MyError {
    IRExpr ret = new IRExpr();
    if(node.op.equals("&&")) {
      // short circuit
      IRExpr lhs = (IRExpr) node.lhs.accept(this);
      String tmp = getIfNumber();

      var dest = new IRVarInfo(irBoolType, getTmpVar());
      // may be omitted?
      var result = new IRArith();
      result.dest = dest;
      result.lhs = result.rhs = irFalse;
      result.op = "and";
      cur.addInst(result);

      var trueBr = new IRBlock(tmp + ".true." + getBlockLabel());
      curFunc.blocks.add(trueBr);
      var end = new IRBlock(tmp + ".end." + getBlockLabel(), cur.exit);
      curFunc.blocks.add(end);
      cur.exit = new IRBranch(trueBr, end, lhs.dest);
      trueBr.exit = new IRJump(end);

      cur = trueBr;
      IRExpr rhs = (IRExpr) node.rhs.accept(this);
      var tf = new IRArith();
      tf.dest = dest;
      tf.lhs = rhs.dest;
      tf.rhs = irFalse;
      tf.op = "or";
      cur.addInst(tf);

      cur = end;

      ret.dest = dest;
      return ret;
    }
    else if(node.op.equals("||")) {
      IRExpr lhs = (IRExpr) node.lhs.accept(this);
      String tmp = getIfNumber();

      var dest = new IRVarInfo(irBoolType, getTmpVar());
      var result = new IRArith();
      result.dest = dest;
      result.lhs = result.rhs = irTrue;
      result.op = "and";
      cur.addInst(result);

      var falseBr = new IRBlock(tmp + ".false." + getBlockLabel());
      curFunc.blocks.add(falseBr);
      var end = new IRBlock(tmp + ".end." + getBlockLabel(), cur.exit);
      curFunc.blocks.add(end);
      cur.exit = new IRBranch(end, falseBr, lhs.dest);
      falseBr.exit = new IRJump(end);

      cur = falseBr;
      IRExpr rhs = (IRExpr) node.rhs.accept(this);
      var tf = new IRArith();
      tf.dest = dest;
      tf.lhs = rhs.dest;
      tf.rhs = irFalse;
      tf.op = "or";
      cur.addInst(tf);

      cur = end;

      ret.dest = dest;
      return ret;
    }
    IRExpr lhs = (IRExpr) node.lhs.accept(this);
    IRExpr rhs = (IRExpr) node.rhs.accept(this);

    if(node.lhs.info.equals(stringType)) {
      var call = new IRCall(); // dest, type, funcName
      call.args = new ArrayList<>();
      call.args.add(lhs.dest);
      call.args.add(rhs.dest);
      if(node.op.equals("+")) {
        call.funcName = "__string.concat";
        call.type = irPtrType;
        ret.dest = call.dest = new IRVarInfo(irPtrType, getTmpVar());
        cur.addInst(call);
        return ret;
      }
      call.funcName = "__string.compare";
      call.type = irIntType;
      call.dest = new IRVarInfo(irIntType, getTmpVar());
      cur.addInst(call);
      var cmp = new IRArith();
      cmp.lhs = call.dest;
      cmp.rhs = irZero;
      ret.dest = cmp.dest = new IRVarInfo(irBoolType, getTmpVar());
      cmp.op = switch(node.op) {
        case "==" -> "eq";
        case "!=" -> "ne";
        case ">" -> "sgt";
        case ">=" -> "sge";
        case "<" -> "slt";
        case "<=" -> "sle";

        default -> throw new InternalError("Binary of string op type unexpected", node.pos);
      };
      cur.addInst(call);
      cur.addInst(cmp);
    }
    else {
      var arith = new IRArith();
      ret.dest = arith.dest = new IRVarInfo(irIntType, getTmpVar());
      arith.lhs = lhs.dest;
      arith.rhs = rhs.dest;

      arith.op = switch(node.op) {
        case "+" -> "add";
        case "-" -> "sub";
        case "*" -> "mul";
        case "/" -> "sdiv";
        case "%" -> "srem";
        case "<<" -> "shl";
        case ">>" -> "ashr";
        case "&" -> "and";
        case "|" -> "or";
        case "^" -> "xor";
        case "==" -> "eq";
        case "!=" -> "ne";
        case ">" -> "sgt";
        case ">=" -> "sge";
        case "<" -> "slt";
        case "<=" -> "sle";

        default -> throw new InternalError("Binary of int op type unexpected", node.pos);
      };
      cur.addInst(arith);
    }
    return ret;
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
    for(var s : node.statements) {
      s.accept(this);
    }
    return null;
  }

  public IRNode visit(VarDefStmt node) throws MyError {
    node.def.accept(this);
    return null;
  }

  public IRNode visit(IfElseStmt node) throws MyError {
    String tmp = getIfNumber();
    var cond = cur;
    var trueBr = new IRBlock(tmp + ".true." + getBlockLabel());
    curFunc.blocks.add(trueBr);
    var end = new IRBlock(tmp + ".end." + getBlockLabel(), cur.exit);
    curFunc.blocks.add(end);
    if(node.falseBranch == null) {
      var condJmp = new IRBranch(trueBr, end);
      trueBr.exit = new IRJump(end);
      cur = cond;
      cond.exit = condJmp;
      IRExpr condExpr = (IRExpr) node.condition.accept(this);
      condJmp.condition = condExpr.dest;
      cur = trueBr;
      node.trueBranch.accept(this);
      cur = end;
    }
    else {
      var falseBr = new IRBlock(tmp + ".false." + getBlockLabel());
      curFunc.blocks.add(falseBr);
      var condJmp = new IRBranch(trueBr, falseBr);
      trueBr.exit = new IRJump(end);
      falseBr.exit = new IRJump(end);
      cur = cond;
      cond.exit = condJmp;
      IRExpr condExpr = (IRExpr) node.condition.accept(this);
      condJmp.condition = condExpr.dest;
      cur = trueBr;
      node.trueBranch.accept(this);
      cur = falseBr;
      node.falseBranch.accept(this);
      cur = end;
    }
    return null;
  }

  public IRNode visit(BreakStmt node) throws MyError {
    var jmp = new IRJump(breakDests.peek());
    addBr(jmp);
    return null;
  }

  public IRNode visit(ContinueStmt node) throws MyError {
    var jmp = new IRJump(continueDests.peek());
    addBr(jmp);
    return null;
  }

  public IRNode visit(RetStmt node) throws MyError {
    IRReturn ins;
    if(node.retVal == null) ins = new IRReturn(irVoidType, null);
    else {
      IRExpr val = (IRExpr) node.retVal.accept(this);
      ins = new IRReturn(val.dest.type, val.dest);
    }
    addBr(ins);
    return null;
  }

  public IRNode visit(WhileStmt node) throws MyError {
    // Remember: dests update
    // TODO
    return null;
  }

  public IRNode visit(ForStmt node) throws MyError {
    // TODO
    return null;
  }

  public IRNode visit(PurExprStmt node) throws MyError {
    node.expr.accept(this);
    return null;
  }

  public IRNode visit(EmptyStmt node) throws MyError {
    return null;
  }
}
