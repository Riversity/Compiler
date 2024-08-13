package ast.node.def;

import ast.node.BaseNode;
import ast.*;
import util.error.MyError;
import util.info.BaseInfo;

public class Def extends BaseNode {
  @Override
  public <T> T accept(AstVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
