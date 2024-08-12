package ast.node.expr;

import ast.AstVisitor;
import ast.node.BaseNode;
import util.error.*;

public final class AtomExpr extends BaseNode {
  public static enum Type {
    THIS, NULL, TF, ID, DEC, STR, CUSTOM
  }
  public Type atom_type;
  public String val;

  /*
  @Override
  public String toString() {
    if (atom_type == Type.STR) {
      return "\"" + val
              .replace("\\", "\\\\")
              .replace("\n", "\\n")
              .replace("\t", "\\t")
              .replace("\"", "\\\"")
              + "\"";
    }
    return val;
  }
  */
  @Override
  public <T> T accept(AstVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
