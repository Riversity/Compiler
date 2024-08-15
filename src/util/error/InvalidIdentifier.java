package util.error;

import util.Position;

public class InvalidIdentifier extends MyError {
  public InvalidIdentifier(String msg, Position pos) {
    super("Invalid Identifier: " + msg, pos);
  }

  @Override
  public String PrintError() {
    return "Invalid Identifier";
  }
}
