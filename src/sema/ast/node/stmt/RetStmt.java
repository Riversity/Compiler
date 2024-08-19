package sema.ast.node.stmt;

import sema.ast.AstVisitor;
import sema.ast.node.expr.Expr;
import sema.util.error.MyError;

public class RetStmt extends Stmt {
  public Expr retVal;

  @Override
  public <T> T accept(AstVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
