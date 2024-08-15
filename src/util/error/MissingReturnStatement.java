package util.error;

import util.Position;

public class MissingReturnStatement extends MyError {
  public MissingReturnStatement(String msg, Position pos) {
    super("Missing Return Statement: " + msg, pos);
  }

  @Override
  public String PrintError() {
    return "Missing Return Statement";
  }
}
