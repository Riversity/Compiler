package ast.node.stmt;

import ast.AstVisitor;
import ast.node.expr.Expr;
import util.error.MyError;
import util.scope.BaseScope;

public class ForStmt extends Stmt {
  public BaseScope scope;
  public Stmt init;
  public Expr condition;
  public PurExprStmt update;
  public Stmt body;

  @Override
  public <T> T accept(AstVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
