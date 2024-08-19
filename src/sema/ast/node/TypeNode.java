package sema.ast.node;

import sema.ast.AstVisitor;
import sema.ast.node.expr.Expr;
import sema.util.error.MyError;
import sema.util.info.TypeInfo;

import java.util.ArrayList;

public class TypeNode extends BaseNode {
  public TypeInfo info;
  public ArrayList<Expr> width;

  @Override
  public <T> T accept(AstVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
