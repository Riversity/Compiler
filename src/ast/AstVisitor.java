package ast;

import ast.node.*;
import ast.node.expr.*;

public interface AstVisitor<T> {
  public T visit(BaseNode node) throws Exception;
  public T visit(Atom node) throws Exception;
}
