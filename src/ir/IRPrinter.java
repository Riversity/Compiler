package ir;

import ir.node.*;
import ir.node.def.IRFuncDef;
import ir.node.def.IRGlobDef;
import sema.util.error.*;
import sema.util.error.InternalError;

import static sema.util.Position.blankPos;

public class IRPrinter implements IRVisitor<String> {
  @Override
  public String visit(IRNode node) throws MyError {
    throw new InternalError("IRNode abstract visit should not be called", blankPos);
  }

  @Override
  public String visit(IRFuncDef node) throws MyError {
    StringBuilder str = new StringBuilder("define " + node.returnType.toString() + " @" + node.name + "(");
    // str += node.params.toString(", ") + ") {\n";
    // str += node.blocks.toString("\n");
    for(var v : node.params) {
      str.append(v.toString()).append(", ");
    }
    str.append(") {\n");
    for(var v : node.blocks) {
      str.append(v.accept(this));
      str.append("\n");
    }
    str.append("\n}");
    return str.toString();
  }

  @Override
  public String visit(IRGlobDef node) throws MyError {
    String ret = node.info.val + " = global ";
  }
}
