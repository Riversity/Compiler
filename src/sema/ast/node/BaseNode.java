package ast.node;

import util.Position;
import ast.AstVisitor;
import util.error.MyError;

public abstract class BaseNode {
  public Position pos;
  public <T> T accept(AstVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}