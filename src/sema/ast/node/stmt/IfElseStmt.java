package sema.ast.node.stmt;

import sema.ast.AstVisitor;
import sema.ast.node.expr.Expr;
import sema.util.error.MyError;
import sema.util.scope.BaseScope;

public class IfElseStmt extends Stmt {
  public Expr condition;
  public BaseScope trueScope, falseScope;
  public Stmt trueBranch, falseBranch;

  @Override
  public <T> T accept(AstVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
