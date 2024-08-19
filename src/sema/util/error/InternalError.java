package util.error;

import util.Position;

public class InternalError extends MyError {
  public InternalError(String msg, Position pos) {
    super("Internal Error: " + msg, pos);
  }

  @Override
  public String printError() {
    return "Internal Error";
  }
}
