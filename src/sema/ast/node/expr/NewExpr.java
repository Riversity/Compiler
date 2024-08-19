package sema.ast.node.expr;

import sema.ast.AstVisitor;
import sema.ast.node.TypeNode;
import sema.util.error.MyError;

public class NewExpr extends Expr {
  public TypeNode type;
  public LiteralML init;

  @Override
  public <T> T accept(AstVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
