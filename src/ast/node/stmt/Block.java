package ast.node.stmt;

import ast.AstVisitor;
import util.error.MyError;
import util.scope.BaseScope;

import java.util.ArrayList;

public class Block extends Stmt {
  public BaseScope scope;
  public ArrayList<Stmt> statements;

  @Override
  public <T> T accept(AstVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
