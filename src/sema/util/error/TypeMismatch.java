package sema.util.error;

import sema.util.Position;

public class TypeMismatch extends MyError {
  public TypeMismatch(String msg, Position pos) {
    super("Type Mismatch: " + msg, pos);
  }

  @Override
  public String printError() {
    return "Type Mismatch";
  }
}
