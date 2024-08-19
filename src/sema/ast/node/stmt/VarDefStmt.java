package ast.node.stmt;

import ast.AstVisitor;
import ast.node.def.VarDef;
import util.error.MyError;

public class VarDefStmt extends Stmt {
  public VarDef def;

  @Override
  public <T> T accept(AstVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
