package sema.ast.node.expr;

import sema.ast.AstVisitor;
import sema.util.error.MyError;

public class UnaryExpr extends Expr {
  public String op;
  public Expr object;

  @Override
  public <T> T accept(AstVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
