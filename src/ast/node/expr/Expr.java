package ast.node.expr;

import ast.AstVisitor;
import ast.node.BaseNode;
import util.error.MyError;
import util.info.*;

public class Expr extends BaseNode {
  public BaseInfo info;
  public boolean isLValue;

  @Override
  public <T> T accept(AstVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
