package sema.ast.node.stmt;

import sema.ast.AstVisitor;
import sema.ast.node.BaseNode;
import sema.util.error.MyError;

public class Stmt extends BaseNode {
  @Override
  public <T> T accept(AstVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
