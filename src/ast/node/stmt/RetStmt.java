package ast.node.stmt;

import ast.AstVisitor;
import ast.node.expr.Expr;
import util.error.MyError;

public class RetStmt extends Stmt {
  public Expr retVal;

  @Override
  public <T> T accept(AstVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
