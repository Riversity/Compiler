package ir;

import ir.node.*;
import ir.node.def.*;
import ir.node.ins.*;
import ir.node.stmt.*;
import sema.util.error.*;
import sema.util.error.InternalError;

import static ir.util.IRNative.*;
import static ir.util.IRUtil.arrayToString;
import static sema.util.Position.blankPos;

public class IRPrinter implements IRVisitor<String> {
  @Override
  public String visit(IRNode node) throws MyError {
    throw new InternalError("IRNode abstract visit should not be called", blankPos);
  }

  @Override
  public String visit(IRRoot node) throws MyError {
    StringBuilder str = new StringBuilder();
    for(var d : node.defs) {
      str.append(d.accept(this)).append("\n");
    }
    str.append("\n");
    for(var f : irBuiltInFuncs) {
      str.append(f.accept(this)).append("\n");
    }
    str.append("declare ptr @__alloc(i32, i32, i32, ...)\n\n");
    for(var f : node.funcs) {
      str.append(f.accept(this)).append("\n\n");
    }
    return str.toString();
  }

  @Override
  public String visit(IRFuncDef node) throws MyError {
    StringBuilder str = new StringBuilder("define " + node.returnType.toString() + " @" + node.name + "(");
    str.append(arrayToString(node.params, ", "));
    str.append(") {\n");
    for(var v : node.blocks) {
      str.append(v.accept(this)).append("\n");
    }
    str.append("}");
    return str.toString();
  }

  @Override
  public String visit(IRFuncDecl node) throws MyError {
    return "declare " + node.returnType.toString() + " @" + node.name + "(" +
            arrayToString(node.params, ", ") + ")";
  }

  @Override
  public String visit(IRGlobDef node) throws MyError {
    if(node.info.type.equals(irIntType) || node.info.type.equals(irBoolType)) {
      return node.info.name + " = global " + node.info.type + " 0";
    }
    else if(node.info.type instanceof IRStructType) {
      return node.info.name + " = global " + node.info.type.toString();
    }
    else return node.info.name + " = global ptr null";
  }

  @Override
  public String visit(IRStrDef node) throws MyError {
    return node.info.name + " = constant [" + node.length + " x i8] c\"" + node.str + "\"";
  }


  @Override
  public String visit(IRAlloca node) throws MyError {
    return node.dest.name + " = alloca " + node.type;
  }

  @Override
  public String visit(IRArith node) throws MyError {
    if(node.isCmp()) return node.dest.name + " = icmp " + node.op + " "
            + node.lhs.type + " " + node.lhs.name + ", " + node.rhs.name;
    else return node.dest.name + " = " + node.op + " "
            + node.lhs.type + " " + node.lhs.name + ", " + node.rhs.name;
  }

  @Override
  public String visit(IRBranch node) throws MyError {
    return "br " + node.condition.toString() + ", label %" + node.trueLabel + ", label %" + node.falseLabel;
  }

  @Override
  public String visit(IRCall node) throws MyError {
    return (node.dest == null ? "" : node.dest.name + " = ") + "call " + node.type.toString()
            + (node.funcName.equals("__alloc") ? " (i32, i32, i32, ...)" : "")
            + " @" + node.funcName + "(" + arrayToString(node.args, ", ") + ")";
  }

  @Override
  public String visit(IRGetElementPtr node) throws MyError {
    return node.dest.name + " = getelementptr " + node.type + ", "
            + node.src + (node.isMember ? ", i32 0, " : ", ") + node.index;
  }

  @Override
  public String visit(IRJump node) throws MyError {
    return "br label %" + node.label;
  }

  @Override
  public String visit(IRLoad node) throws MyError {
    return node.dest.name + " = load " + node.dest.type + ", ptr " + node.src.name;
  }

  @Override
  public String visit(IRReturn node) throws MyError {
    return "ret " + node.type + (node.type.equals(irVoidType) ? "" : " " + node.val.name);
  }

  @Override
  public String visit(IRStore node) throws MyError {
    return "store " + node.src + ", ptr " + node.dest.name;
  }

  @Override
  public String visit(IRBlock node) throws MyError {
    StringBuilder str = new StringBuilder();
    str.append(node.label).append(":\n");
    for(var i : node.insts) {
      str.append("  ").append(i.accept(this)).append("\n");
    }
    return str.toString();
  }
}
