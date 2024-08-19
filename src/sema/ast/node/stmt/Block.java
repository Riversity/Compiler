package sema.ast.node.stmt;

import sema.ast.AstVisitor;
import sema.util.error.MyError;
import sema.util.scope.BaseScope;

import java.util.ArrayList;

public class Block extends Stmt {
  public BaseScope scope;
  public ArrayList<Stmt> statements;

  public Block() {
    statements = new ArrayList<>();
  }
  @Override
  public <T> T accept(AstVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
