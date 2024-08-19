package sema.util.error;

import sema.util.Position;

public class InvalidControlFlow extends MyError {
  public InvalidControlFlow(String msg, Position pos) {
    super("Invalid Control Flow: " + msg, pos);
  }

  @Override
  public String printError() {
    return "Invalid Control Flow";
  }
}
