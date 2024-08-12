package ast.node.def;

import ast.AstVisitor;
import ast.node.expr.*;
import util.error.MyError;

public class VarDef extends BaseDef {
  public Expr init;
  
  @Override
  public <T> T accept(AstVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
