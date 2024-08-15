package util.error;

import util.Position;

abstract public class MyError extends RuntimeException {
  public abstract String PrintError();
  private final String message;
  private final Position pos;
  public MyError(String message, Position pos) {
    this.message = message;
    this.pos = pos;
  }
  public String toString() {
    return pos.toString() + message;
  }
}
