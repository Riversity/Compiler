package ast.node;

import util.Position;
import ast.AstVisitor;

public abstract class BaseNode {
  protected Position pos;
  abstract public <T> T accept(AstVisitor<T> visitor) throws Exception;
}
