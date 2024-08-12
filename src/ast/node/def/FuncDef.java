package ast.node.def;

import ast.AstVisitor;
import ast.node.stmt.Block;
import util.error.MyError;
import util.scope.FuncScope;

import java.util.ArrayList;

public class FuncDef extends BaseDef {
  public FuncScope scope;
  public ArrayList<VarDef> params;
  public Block body;

  @Override
  public <T> T accept(AstVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
