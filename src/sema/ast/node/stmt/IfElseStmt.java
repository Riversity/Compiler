package ast.node.stmt;

import ast.AstVisitor;
import ast.node.expr.Expr;
import util.error.MyError;
import util.scope.BaseScope;

public class IfElseStmt extends Stmt {
  public Expr condition;
  public BaseScope trueScope, falseScope;
  public Stmt trueBranch, falseBranch;

  @Override
  public <T> T accept(AstVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
