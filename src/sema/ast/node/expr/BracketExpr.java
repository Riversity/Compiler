package sema.ast.node.expr;

import sema.ast.AstVisitor;
import sema.util.error.MyError;

public class BracketExpr extends Expr {
  public Expr array;
  public Expr index;

  @Override
  public <T> T accept(AstVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
