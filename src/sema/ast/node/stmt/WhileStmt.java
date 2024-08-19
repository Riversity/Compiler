package sema.ast.node.stmt;

import sema.ast.AstVisitor;
import sema.ast.node.expr.Expr;
import sema.util.error.MyError;
import sema.util.scope.BaseScope;

public class WhileStmt extends Stmt {
  public BaseScope scope;
  public Expr condition;
  public Stmt body;

  @Override
  public <T> T accept(AstVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
