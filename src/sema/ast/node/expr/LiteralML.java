package sema.ast.node.expr;

import sema.ast.AstVisitor;
import sema.util.error.MyError;

import java.util.ArrayList;

public class LiteralML extends Expr {
  public static enum Type {
    THIS, NULL, TF, DEC, STR, ANY, UNKNOWN
  }
  public Type type;
  public ArrayList<String> atomList;
  public ArrayList<LiteralML> list;
  // public int dimension; // record in type info

  @Override
  public <T> T accept(AstVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
