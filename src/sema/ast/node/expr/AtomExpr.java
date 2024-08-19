package sema.ast.node.expr;

import sema.ast.AstVisitor;
import sema.util.error.MyError;

public final class AtomExpr extends Expr {
  public static enum Type {
    THIS, NULL, TF, DEC, STR, ID, CUSTOM
  }
  public Type atomType;
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
