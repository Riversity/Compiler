package sema.ast.node.def;

import sema.ast.node.BaseNode;
import sema.ast.AstVisitor;
import sema.util.error.MyError;

public class Def extends BaseNode {
  @Override
  public <T> T accept(AstVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
