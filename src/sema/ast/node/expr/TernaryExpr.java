package sema.ast.node.expr;

import sema.ast.AstVisitor;
import sema.util.error.MyError;

public class TernaryExpr extends Expr {
  public Expr condition;
  public Expr trueBranch;
  public Expr falseBranch;

  @Override
  public <T> T accept(AstVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
