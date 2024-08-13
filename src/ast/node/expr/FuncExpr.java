package ast.node.expr;

import ast.AstVisitor;
import util.error.MyError;

import java.util.ArrayList;

public class FuncExpr extends Expr {
  public Expr func;
  public ArrayList<Expr> args;

  @Override
  public <T> T accept(AstVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
