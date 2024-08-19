package sema.ast.node.def;

import sema.ast.AstVisitor;
import sema.ast.node.TypeNode;
import sema.ast.node.stmt.Block;
import org.antlr.v4.runtime.misc.Pair;
import sema.util.error.MyError;
import sema.util.info.FuncInfo;
import sema.util.scope.BaseScope;

import java.util.ArrayList;

public class FuncDef extends Def {
  public BaseScope scope;
  public FuncInfo info;

  public String name;
  public TypeNode retType;
  public ArrayList<Pair<TypeNode, String>> params;
  public Block body;

  @Override
  public <T> T accept(AstVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
