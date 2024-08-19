package sema.ast.node;

import sema.ast.AstVisitor;
import sema.ast.node.def.Def;
import sema.util.error.MyError;
import sema.util.scope.GlobalScope;

import java.util.ArrayList;

public class Program extends BaseNode {
  public GlobalScope scope;
  public ArrayList<Def> defs;

  @Override
  public <T> T accept(AstVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
