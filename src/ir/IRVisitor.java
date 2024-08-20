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
  public T visit(IRClassDef node) throws MyError;
  public T visit(IRGlobDef node) throws MyError;
  public T visit(IRStrDef node) throws MyError;

  public T visit(IRAllocaNode node) throws MyError;
  public T visit(IRArithNode node) throws MyError;
  public T visit(IRBranchNode node) throws MyError;
  public T visit(IRCallNode node) throws MyError;
  public T visit(IRGetElementPtrNode node) throws MyError;
  public T visit(IRJumpNode node) throws MyError;
  public T visit(IRLoadNode node) throws MyError;
  public T visit(IRPhiNode node) throws MyError;
  public T visit(IRReturnNode node) throws MyError;
  public T visit(IRStoreNode node) throws MyError;
  public T visit(IRCustomNode node) throws MyError;
  public T visit(IRLabelNode node) throws MyError;

  public T visit(IRBlock node) throws MyError;
}
