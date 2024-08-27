package sema.ast.node.def;

import sema.ast.AstVisitor;
import sema.ast.node.stmt.Block;
import sema.util.error.MyError;
import sema.util.info.ClassInfo;
import sema.util.scope.ClassScope;

import java.util.ArrayList;
import java.util.HashMap;

public class ClassDef extends Def {
  public ClassScope scope;
  public ClassInfo info;

  public String name;
  public Block constructor;
  public ArrayList<VarDef> vars;
  public ArrayList<FuncDef> funcs;

  public HashMap<String, Integer> offset;

  @Override
  public <T> T accept(AstVisitor<T> visitor) throws MyError {
    return visitor.visit(this);
  }
}
