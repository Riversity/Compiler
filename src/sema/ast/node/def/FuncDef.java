package ast.node.def;

import ast.AstVisitor;
import ast.node.TypeNode;
import ast.node.stmt.Block;
import org.antlr.v4.runtime.misc.Pair;
import util.error.MyError;
import util.info.FuncInfo;
import util.info.TypeInfo;
import util.scope.BaseScope;

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
