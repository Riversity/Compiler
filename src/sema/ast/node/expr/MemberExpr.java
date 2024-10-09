package sema.ast.node.expr;

import sema.ast.AstVisitor;
import sema.util.error.MyError;
import sema.util.info.ClassInfo;

public class MemberExpr extends Expr {
  public Expr expr;
  public String member;

  public boolean isArraySize = false;
  public ClassInfo classInfo;

  @Override
  public <T> T accept(AstVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
