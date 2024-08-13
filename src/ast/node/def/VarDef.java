package ast.node.def;

import ast.AstVisitor;
import ast.node.expr.*;
import org.antlr.v4.runtime.misc.Pair;
import util.error.MyError;

import java.util.ArrayList;

public class VarDef extends Def {
  public ArrayList<Pair<String, Expr>> list;

  @Override
  public <T> T accept(AstVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
