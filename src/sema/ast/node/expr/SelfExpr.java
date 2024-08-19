package sema.ast.node.expr;

import sema.ast.AstVisitor;
import sema.util.error.MyError;

public class SelfExpr extends Expr {
  public Expr object;
  public String op;

  @Override
  public <T> T accept(AstVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
