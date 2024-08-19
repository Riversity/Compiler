package sema.ast.node.expr;

import sema.ast.AstVisitor;
import sema.util.error.MyError;

public class MemberExpr extends Expr {
  public Expr expr;
  public String member;

  @Override
  public <T> T accept(AstVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
