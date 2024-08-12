package ast;

import ast.node.*;
import ast.node.def.*;
import ast.node.expr.*;
import util.error.MyError;

public interface AstVisitor<T> {
  public T visit(BaseNode node) throws MyError;

  public T visit(FuncDef node) throws MyError;
  public T visit(ClassDef node) throws MyError;
  public T visit(VarDef node) throws MyError;

  public T visit(AtomExpr node) throws MyError;
  public T visit(FString node) throws MyError;
  public T visit(Paren node) throws MyError;
  public T visit(Membr node) throws MyError;
  public T visit(Bracket node) throws MyError;
  public T visit(Func node) throws MyError;
  public T visit(Self node) throws MyError;
  public T visit(Unary node) throws MyError;
  public T visit(NewType node) throws MyError;
  public T visit(Binary node) throws MyError;
  public T visit(Assign node) throws MyError;
  public T visit(Ternary node) throws MyError;
}
