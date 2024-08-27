package sema.ast.node.expr;

import sema.ast.AstVisitor;
import sema.util.error.MyError;
import sema.util.scope.BaseScope;

public final class AtomExpr extends Expr {
  public static enum Type {
    THIS, NULL, TF, DEC, STR, ID, CUSTOM
  }
  public Type atomType;
  public String val;

  public BaseScope whereToFind;

  /*
  @Override
  public String toString() {
    if (atom_type == Type.STR) {
      return "\"" + name
              .replace("\\", "\\\\")
              .replace("\n", "\\n")
              .replace("\t", "\\t")
              .replace("\"", "\\\"")
              + "\"";
    }
    return name;
  }
  */
  @Override
  public <T> T accept(AstVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
