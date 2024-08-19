package sema.util.error;

import sema.util.Position;

public class InvalidType extends MyError {
  public InvalidType(String msg, Position pos) {
    super("Invalid Type: " + msg, pos);
  }

  @Override
  public String printError() {
    return "Invalid Type";
  }
}
