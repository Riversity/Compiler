package sema.util.error;

import sema.util.Position;

public class MissingReturnStatement extends MyError {
  public MissingReturnStatement(String msg, Position pos) {
    super("Missing Return Statement: " + msg, pos);
  }

  @Override
  public String printError() {
    return "Missing Return Statement";
  }
}
