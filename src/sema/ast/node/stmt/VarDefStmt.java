package sema.ast.node.stmt;

import sema.ast.AstVisitor;
import sema.ast.node.def.VarDef;
import sema.util.error.MyError;

public class VarDefStmt extends Stmt {
  public VarDef def;

  @Override
  public <T> T accept(AstVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
