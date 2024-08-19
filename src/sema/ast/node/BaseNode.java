package sema.ast.node;

import sema.util.Position;
import sema.ast.AstVisitor;
import sema.util.error.MyError;

public abstract class BaseNode {
  public Position pos;
  public <T> T accept(AstVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}