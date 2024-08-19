package ast.node.expr;

import ast.AstVisitor;
import util.error.MyError;

public class MemberExpr extends Expr {
  public Expr expr;
  public String member;

  @Override
  public <T> T accept(AstVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
