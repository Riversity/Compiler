package ast.node;

import ast.AstVisitor;
import ast.node.expr.Expr;
import util.error.MyError;
import util.info.TypeInfo;

import java.util.ArrayList;

public class TypeNode extends BaseNode {
  public TypeInfo info;
  public ArrayList<Expr> width;

  @Override
  public <T> T accept(AstVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
