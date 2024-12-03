package codegen;

import codegen.node.AsmBlock;
import codegen.node.AsmRoot;
import codegen.node.def.*;
import codegen.node.ins.*;
import ir.IRPrinter;
import ir.IRVisitor;
import ir.node.*;
import ir.node.def.*;
import ir.node.ins.*;
import ir.node.stmt.IRBlock;
import sema.util.error.InternalError;
import sema.util.error.MyError;

import static sema.util.Position.blankPos;

public class AsmBuilder implements IRVisitor<String> {
  public AsmRoot root;
  public AsmBlock cur;
  public MemManager mem;
  public IRPrinter debug = new IRPrinter();
  boolean isScan = true;
  int offset;

  @Override
  public String visit(IRNode node) throws MyError {
    throw new InternalError("IRNode abstract visit should not be called", blankPos);
  }

  @Override
  public String visit(IRRoot node) throws MyError {
    if(isScan) {
      root = new AsmRoot();
      mem = new MemManager();
      for(var func : node.funcs) {
        func.accept(this);
      }
      isScan = false;
      return null;
    }
    for(var def : node.defs) {
      def.accept(this);
    }
    for(var func : node.funcs) {
      func.accept(this);
    }
    return null;
  }

  @Override
  public String visit(IRFuncDef node) throws MyError {
    if(isScan) {
      offset = 8; // 0 reserved, 4 for ra
      for(var p : node.params) {
        mem.add(p, offset);
        offset += 4;
      }
      for(var b : node.blocks) {
        b.accept(this);
      }
      node.offset = offset;
      return null;
    }
    var func = new AsmFunc(node.name);

    int i = 0;
    var intro = new AsmBlock(node.name);

    offset = node.offset;
    intro.add(new IIns("addi", "sp", "sp", -offset));
    intro.add(new SIns("sw", "sp", "ra", 4));
    for(var p : node.params) {
      // count > 8 ?
      intro.add(mem.store(p, "a" + i));
      ++i;
    }
    func.body.add(intro);

    for(var b : node.blocks) {
      cur = new AsmBlock(b.label);
      b.accept(this);
      func.body.add(cur);
      cur = null;
    }

    root.funcs.add(func);
    return null;
  }

  @Override
  public String visit(IRFuncDecl node) throws MyError {
    // nothing to do
    return null;
  }

  @Override
  public String visit(IRGlobDef node) throws MyError {
    if(isScan) return null;
    if(node.info.type instanceof IRStructType) {
      return null;
    }
    AsmGlobVar glob = new AsmGlobVar(node.info.name.substring(1), 0);
    root.vars.add(glob);
    return null;
  }

  @Override
  public String visit(IRStrDef node) throws MyError {
    if(isScan) return null;
    String strnew = node.str.replace("\\0A", "\\n")
            .replace("\\22", "\\\"").replace("\\00", "");
    var strDef = new AsmStr(node.info.name.substring(1), strnew);
    root.strs.add(strDef);
    return null;
  }

  @Override
  public String visit(IRAlloca node) throws MyError {
    if(isScan) {
      mem.add(node.dest, offset);
      offset += 4;
      node.offset = offset;
      offset += 4;
      return null;
    }
    IIns calcPos = new IIns("addi", "t6", "sp", node.offset);
    cur.add(calcPos);
    cur.add(mem.store(node.dest, "t6"));
    return null;
  }

  @Override
  public String visit(IRArith node) throws MyError {
    if(isScan) {
      mem.add(node.dest, offset);
      offset += 4;
      return null;
    }
    // t4 = t5 % t6
    cur.add(mem.extract(node.lhs, "t5"));
    cur.add(mem.extract(node.rhs, "t6"));
    if(!node.isCmp()) {
      String opnew = switch(node.op) {
        case "sdiv" -> "div";
        case "srem" -> "rem";
        case "shl" -> "sll";
        case "ashr" -> "sra";
        default -> node.op;
      };
      cur.add(new RIns(opnew, "t4", "t5", "t6"));
    }
    else {
      switch(node.op) {
        case "eq":
          cur.add(new RIns("xor", "t4", "t5", "t6"));
          cur.add(new CustomIns("seqz t4, t4"));
          break;
        case "neq":
          cur.add(new RIns("xor", "t4", "t5", "t6"));
          cur.add(new CustomIns("snez t4, t4"));
          break;
        case "sgt":
          cur.add(new RIns("slt", "t4", "t6", "t5"));
          break;
        case "sge":
          cur.add(new RIns("slt", "t4", "t5", "t6"));
          cur.add(new IIns("xori", "t4", "t4", 1));
          break;
        case "slt":
          cur.add(new RIns("slt", "t4", "t5", "t6"));
          break;
        case "sle":
          cur.add(new RIns("slt", "t4", "t6", "t5"));
          cur.add(new IIns("xori", "t4", "t4", 1));
          break;
      }
    }
    cur.add(mem.store(node.dest, "t4"));
    return null;
  }

  @Override
  public String visit(IRBranch node) throws MyError {
    if(isScan) return null;
    cur.add(mem.extract(node.condition, "t6"));
    cur.add(new CustomIns("beqz t6, " + node.falseEnd.label + ".tmp"));
    cur.add(new CustomIns("j " + node.trueEnd.label));
    cur.add(new CustomIns(node.falseEnd.label + ".tmp:"));
    cur.add(new CustomIns("j " + node.falseEnd.label));
    return null;
  }

  @Override
  public String visit(IRCall node) throws MyError {
    if(isScan) {
      if(node.dest != null) {
        mem.add(node.dest, offset);
        offset += 4;
      }
      return null;
    }
    int i = 0;
    for(var p : node.args) {
      cur.add(mem.extract(p, "a" + i));
      ++i;
    }
    cur.add(new CustomIns("call " + node.funcName));
    if(node.dest != null) {
      cur.add(mem.store(node.dest, "a0"));
    }
    return null;
  }

  @Override
  public String visit(IRGetElementPtr node) throws MyError {
    if(isScan) {
      mem.add(node.dest, offset);
      offset += 4;
      return null;
    }
    cur.add(new CustomIns("# " + debug.visit(node)));
    cur.add(mem.extract(node.src, "t5"));
    cur.add(mem.extract(node.index, "t6"));
    //if(node.isMember) { // first load the object ptr pointing to
    //  cur.add(new LIns("lw", "t5", "t5", 0));
    //}
    cur.add(new IIns("slli", "t6", "t6", 2)); // 4 * index
    cur.add(new RIns("add", "t4", "t5", "t6"));
    cur.add(mem.store(node.dest, "t4"));
    return null;
  }

  @Override
  public String visit(IRJump node) throws MyError {
    if(isScan) return null;
    cur.add(new CustomIns("j " + node.end.label));
    return null;
  }

  @Override
  public String visit(IRLoad node) throws MyError {
    if(isScan) {
      mem.add(node.dest, offset);
      offset += 4;
      return null;
    }
    cur.add(new CustomIns("# " + debug.visit(node)));
    cur.add(mem.extract(node.src, "t6"));
    cur.add(new LIns("lw", "t4", "t6", 0));
    cur.add(mem.store(node.dest, "t4"));
    return null;
  }

  @Override
  public String visit(IRReturn node) throws MyError {
    if(isScan) return null;
    if(node.val != null) {
      var res = mem.extract(node.val, "a0");
      cur.add(res);
    }
    cur.add(new LIns("lw", "ra", "sp", 4));
    cur.add(new IIns("addi", "sp", "sp", offset));
    cur.add(new CustomIns("ret"));
    return null;
  }

  @Override
  public String visit(IRStore node) throws MyError {
    if(isScan) return null;
    cur.add(new CustomIns("# " + debug.visit(node)));
    cur.add(mem.extract(node.src, "t6"));
    cur.add(mem.extract(node.dest, "t4"));
    cur.add(new SIns("sw", "t4", "t6", 0));
    return null;
  }

  @Override
  public String visit(IRPhi node) throws MyError {
    throw new InternalError("Phi not implemented", blankPos);
  }

  @Override
  public String visit(IRBlock node) throws MyError {
    for(var i : node.insts) {
      i.accept(this);
    }
    node.exit.accept(this);
    return null;
  }
}
