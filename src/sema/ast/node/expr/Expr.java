package sema.ast.node.expr;

import sema.ast.AstVisitor;
import sema.ast.node.BaseNode;
import sema.util.error.MyError;
import sema.util.info.BaseInfo;

public class Expr extends BaseNode {
  public BaseInfo info;
  public boolean isLValue;

  @Override
  public <T> T accept(AstVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
