package ast.node.expr;

import ast.AstVisitor;
import util.error.MyError;

import java.util.ArrayList;

public class FStrExpr extends Expr {
  String fHead;
  ArrayList<String> fBody;
  String fTail;

  @Override
  public <T> T accept(AstVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
