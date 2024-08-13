package ast.node.stmt;

import ast.AstVisitor;
import ast.node.def.VarDef;
import ast.node.expr.Expr;
import util.error.MyError;
import util.scope.BaseScope;

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
