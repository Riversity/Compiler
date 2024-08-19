package sema.ast.node.stmt;

import sema.ast.AstVisitor;
import sema.util.error.MyError;

public class ContinueStmt extends Stmt {
  @Override
  public <T> T accept(AstVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
