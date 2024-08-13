package ast.node.expr;

import ast.AstVisitor;
import util.error.MyError;

import java.util.ArrayList;

public class LiteralML extends Expr {
  public ArrayList<LiteralML> list;
  int dimension;

  @Override
  public <T> T accept(AstVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
