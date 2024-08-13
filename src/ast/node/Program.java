package ast.node;

import ast.AstVisitor;
import ast.node.def.Def;
import util.error.MyError;
import util.scope.GlobalScope;

import java.util.ArrayList;

public class Program extends BaseNode {
  public GlobalScope scope;
  public ArrayList<Def> defs;

  @Override
  public <T> T accept(AstVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
