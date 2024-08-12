package ast.node.def;

import ast.AstVisitor;
import util.error.MyError;
import util.info.ClassInfo;
import util.scope.ClassScope;

import java.util.ArrayList;

public class ClassDef extends BaseDef {
  public ClassScope classScope;
  public ClassInfo classInfo;

  public FuncDef constructor;
  public ArrayList<VarDef> vars;
  public ArrayList<FuncDef> funcs;

  @Override
  public <T> T accept(AstVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
