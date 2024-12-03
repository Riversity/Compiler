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
import java.util.HashMap;
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
    sizeQuery = new HashMap<>();
    sizeQuery.put("i1", 1);
    sizeQuery.put("i32", 4);
    sizeQuery.put("ptr", 4);

    var init = new IRFuncDef();
    init.name = "__init";
    init.params = new ArrayList<>();
    init.blocks = new ArrayList<>();
    init.blocks.add(new IRBlock("__init.init", new IRReturn(irVoidType, null)));
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
        calcSize(((ClassDef) def).name, structMember);
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

        var ctorNode = new FuncDef();
        ctorNode.name = "__ctor";
        // class name will be attached when accepting
        ctorNode.params = new ArrayList<>();
        ctorNode.body = ((ClassDef) def).constructor;
        ctorNode.retType = new TypeNode();
        ctorNode.retType.width = new ArrayList<>();
        ctorNode.retType.info = new TypeInfo("void", 0);
        IRFuncDef ctor = (IRFuncDef) ctorNode.accept(this);
        ret.funcs.add(ctor);
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
    if(curClassName != null) ret.params.add(new IRVarInfo(irPtrType, "%this." + curFunc.name));
    String name1 = getBlockLabel();
    String name2 = getBlockLabel();
    IRBlock curBlock = new IRBlock(name1, new IRJump());
    cur = curBlock;
    IRConstInfo val = new IRConstInfo(ret.returnType, ret.returnType.equals(irPtrType) ? "null" : "0");
    IRBlock nextBlock = new IRBlock(name2, new IRReturn(ret.returnType, val));
    ((IRJump) curBlock.exit).end = nextBlock;
    ret.blocks.add(curBlock);
    ret.blocks.add(nextBlock);
    for(var p : node.params) {
      var type = new IRType(p.a.info);
      var name = rename(p.b, node.scope);
      var origin = new IRVarInfo(type, name + "origin");
      ret.params.add(origin);
      IRAlloca alloc = new IRAlloca();
      alloc.type = type;
      alloc.dest = new IRVarInfo(irPtrType, name);
      cur.addInst(alloc);
      IRStore store = new IRStore();
      store.src = origin;
      store.dest = alloc.dest;
      cur.addInst(store);
    }
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
        IRExpr exprRes;
        if(d.b instanceof LiteralML) {
          NewExpr newExpr = new NewExpr();
          newExpr.type = node.type;
          newExpr.init = (LiteralML) d.b;
          exprRes = (IRExpr) newExpr.accept(this);
        }
        else {
          exprRes = (IRExpr) d.b.accept(this);
        }
        var inst = new IRStore();
        inst.dest = alloc.dest;
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
        ret.dest = new IRConstInfo(irPtrType, "null");
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
            tmpName = getTmpVar();
            var getele = new IRGetElementPtr();
            int offset = ((ClassInfo) node.whereToFind.info).offset.get(node.val);
            getele.isMember = true;
            getele.index = new IRConstInfo(irIntType, Integer.toString(offset));
            getele.type = new IRType("%class." + curClassName);
            getele.src = new IRVarInfo(irPtrType, "%this." + curFunc.name);
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
        ret.dest = new IRVarInfo(irPtrType, "%this." + curFunc.name);
        break;
      default:
        throw new InternalError("IRBuilder AtomExpr failed", node.pos);
    }
    return ret;
  }

  public IRNode visit(FStrExpr node) throws MyError {
    var ret = new IRExpr();

    if(node.fAtom != null) {
      var strPtr = new IRVarInfo(irPtrType, getStrVar());
      strDefs.add(new IRStrDef(strPtr, fHeadConvert(node.fAtom)));
      ret.dest = strPtr;
    }
    else {
      var strPtr = new IRVarInfo(irPtrType, getStrVar());
      strDefs.add(new IRStrDef(strPtr, fHeadConvert(node.fHead)));
      ret.dest = strPtr;
      // TODO
    }
    return ret;
  }

  public IRNode visit(MemberExpr node) throws MyError {
    // for func: see implementation for funcexpr
    String irStructName = "%class." + node.classInfo.name;
    var expr = (IRExpr) node.expr.accept(this);
    var destAddr = new IRVarInfo(irPtrType, getTmpVar());
    var dest = new IRVarInfo(new IRType((TypeInfo) node.info), getTmpVar());
    Integer offset = node.classInfo.offset.get(node.member);
    var getele = new IRGetElementPtr();
    getele.isMember = true;
    getele.index = new IRConstInfo(irIntType, offset.toString());
    getele.type = new IRType(irStructName);
    getele.src = expr.dest;
    getele.dest = destAddr;
    cur.addInst(getele);
    var load = new IRLoad(dest, destAddr);
    cur.addInst(load);

    var ret = new IRExpr();
    ret.destAddr = destAddr;
    ret.dest = dest;
    return ret;
  }

  public IRNode visit(BracketExpr node) throws MyError {
    var arr = (IRExpr) node.array.accept(this);
    var idx = (IRExpr) node.index.accept(this);
    var type = new IRType(((TypeInfo) node.info));
    var dest = new IRVarInfo(type, getTmpVar());
    var destAddr = new IRVarInfo(irPtrType, getTmpVar());
    var getele = new IRGetElementPtr();
    getele.isMember = false;
    getele.type = type;
    getele.dest = destAddr;
    getele.src = arr.dest;
    getele.index = idx.dest;
    cur.addInst(getele);
    var load = new IRLoad(dest, destAddr);
    cur.addInst(load);
    return new IRExpr(dest, destAddr);
  }

  public IRNode visit(FuncExpr node) throws MyError {
    var ret = new IRExpr();
    var call = new IRCall();
    call.type = new IRType((TypeInfo) node.info);
    String curCallClass = null;
    IRExpr baseVarInfo;
    call.args = new ArrayList<>();

    if(node.func instanceof MemberExpr) {
      MemberExpr tmp = (MemberExpr) node.func;
      if(tmp.isArraySize) {
        curCallClass = "array";
      }
      else curCallClass = tmp.expr.info.name;
      // if string, this should give "string"
      baseVarInfo = (IRExpr) ((MemberExpr) node.func).expr.accept(this);
      call.args.add(baseVarInfo.dest);
    }
    else if(((AtomExpr) node.func).whereToFind instanceof ClassScope) {
      // ClassDef self reference
      curCallClass = curClassName;
      call.args.add(new IRVarInfo(irPtrType, "%this." + curFunc.name));
    }
    call.funcName = curCallClass != null ? "__" + curCallClass + "." + node.func.info.name : node.func.info.name;

    if(!call.type.equals(irVoidType)) call.dest = new IRVarInfo(call.type, getTmpVar());
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
    var type = node.type;
    var ret = new IRExpr();
    var allocPlace = new IRVarInfo(irPtrType, getTmpVar());
    if(type.width.isEmpty()) {
      var call = new IRCall();
      call.funcName = "malloc";
      call.type = irPtrType;
      call.dest = allocPlace;
      call.args = new ArrayList<>();
      var irType = new IRType(type.info);
      call.args.add(new IRConstInfo(irIntType, checkSize(irType.typeName).toString()));
      cur.addInst(call);
      if(!type.info.isNative) {
        // class constructor
        var ctor = new IRCall();
        ctor.funcName = "__" + type.info.name + ".__ctor";
        ctor.type = irVoidType;
        ctor.args = new ArrayList<>();
        ctor.args.add(allocPlace);
        cur.addInst(ctor);
      }
      ret.dest = allocPlace;
    }
    else {
      var call = new IRCall();
      call.funcName = "__alloc";
      call.type = irPtrType;
      call.dest = allocPlace;
      call.args = new ArrayList<>();
      var irType = new IRType(type.info);
      call.args.add(new IRConstInfo(irIntType, checkSize(irType.typeName).toString()));
      call.args.add(new IRConstInfo(irIntType, Integer.toString(type.info.dimension)));
      call.args.add(new IRConstInfo(irIntType, Integer.toString(type.width.size()))); // == 0 how?
      for(var e : type.width) {
        IRExpr expr = (IRExpr) e.accept(this);
        call.args.add(expr.dest);
      }
      cur.addInst(call);
      ret.dest = allocPlace;
    }
    if(node.init != null) {
      if(node.init.atomList == null || !node.init.type.equals(LiteralML.Type.DEC)) {
        throw new InternalError("High dimension LiteralML not yet implemented", node.pos);
      }
      else {
        int i = 0;
        for(String v : node.init.atomList) {
          var tmpStore = new IRVarInfo(irPtrType, getTmpVar());
          var getele = new IRGetElementPtr();
          getele.type = irIntType;
          getele.isMember = false;
          getele.src = allocPlace;
          getele.dest = tmpStore;
          getele.index = new IRConstInfo(irIntType, Integer.toString(i));
          cur.addInst(getele);
          var store = new IRStore();
          store.src = new IRConstInfo(irIntType, v);
          store.dest = tmpStore;
          cur.addInst(store);
          ++i;
        }
      }
    }
    return ret;
  }

  public IRNode visit(BinaryExpr node) throws MyError {
    IRExpr ret = new IRExpr();
    if(node.op.equals("&&")) {
      // short circuit
      IRExpr lhs = (IRExpr) node.lhs.accept(this);
      String tmp = getIfNumber();

      var dest = new IRVarInfo(irBoolType, getTmpVar());
      var destAddr = new IRVarInfo(irPtrType, getTmpVar());

      var alloc = new IRAlloca();
      alloc.type = irBoolType;
      alloc.dest = destAddr;
      cur.addInst(alloc);

      var storeF = new IRStore();
      storeF.src = irFalse;
      storeF.dest = destAddr;
      cur.addInst(storeF);

      var trueBr = new IRBlock(tmp + ".true." + getBlockLabel());
      curFunc.blocks.add(trueBr);
      var end = new IRBlock(tmp + ".end." + getBlockLabel(), cur.exit);
      curFunc.blocks.add(end);
      cur.exit = new IRBranch(trueBr, end, lhs.dest);
      trueBr.exit = new IRJump(end);

      cur = trueBr;
      IRExpr rhs = (IRExpr) node.rhs.accept(this);
      var storeT = new IRStore();
      storeT.src = rhs.dest;
      storeT.dest = destAddr;
      cur.addInst(storeT);

      cur = end;
      var load = new IRLoad(dest, destAddr);
      cur.addInst(load);
      ret.dest = dest;
      return ret;
    }
    else if(node.op.equals("||")) {
      IRExpr lhs = (IRExpr) node.lhs.accept(this);
      String tmp = getIfNumber();

      var dest = new IRVarInfo(irBoolType, getTmpVar());
      var destAddr = new IRVarInfo(irPtrType, getTmpVar());

      var alloc = new IRAlloca();
      alloc.type = irBoolType;
      alloc.dest = destAddr;
      cur.addInst(alloc);

      var storeT = new IRStore();
      storeT.src = irTrue;
      storeT.dest = destAddr;
      cur.addInst(storeT);

      var falseBr = new IRBlock(tmp + ".false." + getBlockLabel());
      curFunc.blocks.add(falseBr);
      var end = new IRBlock(tmp + ".end." + getBlockLabel(), cur.exit);
      curFunc.blocks.add(end);
      cur.exit = new IRBranch(end, falseBr, lhs.dest);
      falseBr.exit = new IRJump(end);

      cur = falseBr;
      IRExpr rhs = (IRExpr) node.rhs.accept(this);
      var storeF = new IRStore();
      storeF.src = rhs.dest;
      storeF.dest = destAddr;
      cur.addInst(storeF);

      cur = end;
      var load = new IRLoad(dest, destAddr);
      cur.addInst(load);
      ret.dest = dest;
      return ret;
    }
    IRExpr lhs = (IRExpr) node.lhs.accept(this);
    IRExpr rhs = (IRExpr) node.rhs.accept(this);

    if(stringType.equals(node.lhs.info)) {
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
      ret.dest = arith.dest = new IRVarInfo(new IRType((TypeInfo) node.info), getTmpVar());
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
    throw new InternalError("LiteralML should not appear out of new", node.pos);
  }

  public IRNode visit(AssignExpr node) throws MyError {
    IRExpr lhs = (IRExpr) node.lhs.accept(this);
    IRExpr rhs = (IRExpr) node.rhs.accept(this);
    if(stringType.equals(node.rhs.info)) {
      var call = new IRCall();
      call.funcName = "__string.copy";
      call.type = irPtrType;
      call.args = new ArrayList<>();
      call.args.add(lhs.destAddr);
      call.args.add(rhs.dest);
      cur.addInst(call);
    }
    else {
      var store = new IRStore();
      store.dest = (IRVarInfo) lhs.destAddr;
      store.src = rhs.dest;
      cur.addInst(store);
    }
    return lhs;
  }

  public IRNode visit(TernaryExpr node) throws MyError {
    String tmp = getIfNumber();
    IRType type = new IRType((TypeInfo) node.info);
    IRVarInfo resDest = new IRVarInfo(type, getTmpVar());
    IRVarInfo resDestAddr = new IRVarInfo(irPtrType, getTmpVar());
    boolean isNotVoid = !voidType.equals(node.info);

    if(isNotVoid) {
      var alloc = new IRAlloca();
      alloc.type = type;
      alloc.dest = resDestAddr;
      cur.addInst(alloc);
    }

    var cond = cur;
    var trueBr = new IRBlock(tmp + ".true." + getBlockLabel());
    curFunc.blocks.add(trueBr);
    var falseBr = new IRBlock(tmp + ".false." + getBlockLabel());
    curFunc.blocks.add(falseBr);
    var end = new IRBlock(tmp + ".end." + getBlockLabel(), cur.exit);
    curFunc.blocks.add(end);
    trueBr.exit = new IRJump(end);
    falseBr.exit = new IRJump(end);
    var condJmp = new IRBranch(trueBr, falseBr);
    cond.exit = condJmp;

    // cur = cond;
    IRExpr condExpr = (IRExpr) node.condition.accept(this);
    condJmp.condition = condExpr.dest;

    cur = trueBr;
    IRExpr trueVal = (IRExpr) node.trueBranch.accept(this);
    if(isNotVoid) {
      var storeT = new IRStore();
      storeT.dest = resDestAddr;
      storeT.src = trueVal.dest;
      cur.addInst(storeT);
    }

    cur = falseBr;
    IRExpr falseVal = (IRExpr) node.falseBranch.accept(this);
    if(isNotVoid) {
      var storeF = new IRStore();
      storeF.dest = resDestAddr;
      storeF.src = falseVal.dest;
      cur.addInst(storeF);
    }

    cur = end;
    if(isNotVoid) {
      var load = new IRLoad(resDest, resDestAddr);
      cur.addInst(load);
    }
    IRExpr res = new IRExpr();
    res.dest = resDest;
    return res;
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
      // cur = cond;
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
      // cur = cond;
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
    String tmp = "while" + getLoopNumber();
    var in = cur;
    var cond = new IRBlock(tmp + ".cond." + getBlockLabel());
    curFunc.blocks.add(cond);
    var body = new IRBlock(tmp + ".body." + getBlockLabel());
    curFunc.blocks.add(body);
    var end = new IRBlock(tmp + ".end." + getBlockLabel(), cur.exit);
    curFunc.blocks.add(end);

    in.exit = new IRJump(cond);
    body.exit = new IRJump(cond);

    // cur = in;

    // exit must be determined before accepting
    cur = cond;
    var condBr = new IRBranch(body, end);
    cond.exit = condBr;
    var condInfo = (IRExpr) node.condition.accept(this);
    condBr.condition = condInfo.dest;

    cur = body;
    continueDests.add(cond);
    breakDests.add(end);
    node.body.accept(this);
    breakDests.pop();
    continueDests.pop();

    cur = end;
    return null;
  }

  public IRNode visit(ForStmt node) throws MyError {
    String tmp = "for" + getLoopNumber();
    var in = cur;
    var cond = new IRBlock(tmp + ".cond." + getBlockLabel());
    curFunc.blocks.add(cond);
    var chng = new IRBlock(tmp + ".chng." + getBlockLabel());
    curFunc.blocks.add(chng);
    var body = new IRBlock(tmp + ".body." + getBlockLabel());
    curFunc.blocks.add(body);
    var end = new IRBlock(tmp + ".end." + getBlockLabel(), cur.exit);
    curFunc.blocks.add(end);

    in.exit = new IRJump(cond);
    chng.exit = new IRJump(cond);
    body.exit = new IRJump(chng);

    // cur = in;
    if(node.initExpr != null) node.initExpr.accept(this);
    else if(node.initStmt != null) node.initStmt.accept(this);

    cur = cond;
    var condBr = new IRBranch(body, end);
    cond.exit = condBr;
    IRExpr condInfo;
    if(node.condition != null) condInfo = (IRExpr) node.condition.accept(this);
    else condInfo = new IRExpr(irTrue, null);
    condBr.condition = condInfo.dest;

    cur = chng;
    if(node.step != null) node.step.accept(this);

    cur = body;
    continueDests.add(chng);
    breakDests.add(end);
    node.body.accept(this);
    breakDests.pop();
    continueDests.pop();

    cur = end;
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
