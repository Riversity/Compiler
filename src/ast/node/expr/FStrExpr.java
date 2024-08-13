package ast.node.expr;

import ast.AstVisitor;
import util.error.MyError;

import java.util.ArrayList;

public class FStrExpr extends Expr {
  public String fHead;
  public ArrayList<String> fBody;
  public String fTail;
  public String fAtom;

  @Override
  public <T> T accept(AstVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
