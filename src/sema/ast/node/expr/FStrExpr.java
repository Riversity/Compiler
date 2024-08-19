package sema.ast.node.expr;

import sema.ast.AstVisitor;
import sema.util.error.MyError;

import java.util.ArrayList;

public class FStrExpr extends Expr {
  public String fHead;
  public ArrayList<String> fBody;
  public ArrayList<Expr> exprs;
  public String fTail;
  public String fAtom;

  @Override
  public <T> T accept(AstVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
