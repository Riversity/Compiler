package sema.util.error;

import sema.util.Position;

public class InvalidIdentifier extends MyError {
  public InvalidIdentifier(String msg, Position pos) {
    super("Invalid Identifier: " + msg, pos);
  }

  @Override
  public String printError() {
    return "Invalid Identifier";
  }
}
