package ast.node.stmt;

import ast.AstVisitor;
import util.error.MyError;

public class ContinueStmt extends Stmt {
  @Override
  public <T> T accept(AstVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
