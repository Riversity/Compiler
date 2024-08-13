package ast.node.expr;

import ast.AstVisitor;
import util.error.MyError;
import util.info.TypeInfo;

public class NewExpr extends Expr {
  TypeInfo type;
  LiteralML init;

  @Override
  public <T> T accept(AstVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
