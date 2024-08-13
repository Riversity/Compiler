package ast.node;

import ast.AstVisitor;
import util.error.MyError;
import util.info.TypeInfo;

public class TypeNode extends BaseNode {
  public TypeInfo info;
  @Override
  public <T> T accept(AstVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
