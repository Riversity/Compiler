package ast.node.expr;

import org.antlr.v4.*;

import ast.AstVisitor;
import ast.node.BaseNode;
import util.error.*;

public final class Atom extends BaseNode {
  @Override
  public <T> T accept(AstVisitor<T> visitor) throws Exception {
    return visitor.visit(this);
  }
}
