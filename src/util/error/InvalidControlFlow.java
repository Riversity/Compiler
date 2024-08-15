package util.error;

import util.Position;

public class InvalidControlFlow extends MyError {
  public InvalidControlFlow(String msg, Position pos) {
    super("Invalid Control Flow: " + msg, pos);
  }

  @Override
  public String PrintError() {
    return "Invalid Control Flow";
  }
}
