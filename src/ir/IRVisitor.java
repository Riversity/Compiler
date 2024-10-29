package ir;

import ir.node.*;
import ir.node.def.*;
import ir.node.ins.*;
import ir.node.stmt.*;
import sema.util.error.MyError;

public interface IRVisitor<T> {
  public T visit(IRNode node) throws MyError;
  public T visit(IRRoot node) throws MyError;

  public T visit(IRFuncDef node) throws MyError;
  public T visit(IRFuncDecl node) throws MyError;
  public T visit(IRGlobDef node) throws MyError;
  public T visit(IRStrDef node) throws MyError;

  public T visit(IRAlloca node) throws MyError;
  public T visit(IRArith node) throws MyError;
  public T visit(IRBranch node) throws MyError;
  public T visit(IRCall node) throws MyError;
  public T visit(IRGetElementPtr node) throws MyError;
  public T visit(IRJump node) throws MyError;
  public T visit(IRLoad node) throws MyError;
  public T visit(IRReturn node) throws MyError;
  public T visit(IRStore node) throws MyError;
  public T visit(IRPhi node) throws MyError;

  // public T visit(IRCustom node) throws MyError;

  public T visit(IRBlock node) throws MyError;
}
