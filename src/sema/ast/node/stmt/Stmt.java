package ast.node.stmt;

import ast.AstVisitor;
import ast.node.BaseNode;
import util.error.MyError;

public class Stmt extends BaseNode {
  @Override
  public <T> T accept(AstVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
