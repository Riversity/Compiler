package sema.ast.node.expr;

import sema.ast.AstVisitor;
import sema.util.error.MyError;

public class BinaryExpr extends Expr {
  public Expr lhs;
  public String op;
  public Expr rhs;

  @Override
  public <T> T accept(AstVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
