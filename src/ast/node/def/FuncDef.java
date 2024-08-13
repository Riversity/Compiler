package ast.node.def;

import ast.AstVisitor;
import ast.node.stmt.Block;
import util.error.MyError;
import util.info.TypeInfo;
import util.scope.FuncScope;

import java.util.ArrayList;

public class FuncDef extends Def {
  public FuncScope scope;
  public ArrayList<TypeInfo> params;
  public Block body;

  @Override
  public <T> T accept(AstVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
