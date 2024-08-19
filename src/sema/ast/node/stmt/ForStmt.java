package sema.ast.node.stmt;

import sema.ast.AstVisitor;
import sema.ast.node.def.VarDef;
import sema.ast.node.expr.Expr;
import sema.util.error.MyError;
import sema.util.scope.BaseScope;

public class ForStmt extends Stmt {
  public BaseScope scope;
  public VarDef initStmt;
  public Expr initExpr;
  public Expr condition;
  public Expr step;
  public Stmt body;

  @Override
  public <T> T accept(AstVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
