package util.error;

import util.Position;

public class InvalidType extends MyError {
  public InvalidType(String msg, Position pos) {
    super("Invalid Type: " + msg, pos);
  }

  @Override
  public String PrintError() {
    return "Invalid Type";
  }
}
