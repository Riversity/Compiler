package ast.node.def;

import ast.AstVisitor;
import ast.node.stmt.Block;
import util.error.MyError;
import util.info.ClassInfo;
import util.scope.ClassScope;

import java.util.ArrayList;

public class ClassDef extends Def {
  public ClassScope scope;
  public ClassInfo info;

  public String name;
  public Block constructor;
  public ArrayList<VarDef> vars;
  public ArrayList<FuncDef> funcs;

  @Override
  public <T> T accept(AstVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
