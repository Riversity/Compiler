package ast.node.expr;

import ast.AstVisitor;
import util.error.MyError;

public class BracketExpr extends Expr {
  Expr array;
  Expr index;

  @Override
  public <T> T accept(AstVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
