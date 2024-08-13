package ast.node.expr;

import ast.AstVisitor;
import util.error.MyError;

public class AssignExpr extends Expr {
  public Expr lhs;
  public Expr rhs;

  @Override
  public <T> T accept(AstVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
