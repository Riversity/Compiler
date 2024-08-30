package sema.ast.node.def;

import sema.ast.AstVisitor;
import sema.ast.node.TypeNode;
import org.antlr.v4.runtime.misc.Pair;
import sema.ast.node.expr.Expr;
import sema.util.error.MyError;
import sema.util.scope.BaseScope;

import java.util.ArrayList;

public class VarDef extends Def {
  public TypeNode type;
  public ArrayList<Pair<String, Expr>> list;
  public BaseScope scope;

  @Override
  public <T> T accept(AstVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
