package util.error;

import util.Position;

abstract public class MyError extends RuntimeException {
  private String message;
  private Position pos;
  public MyError(String message, Position pos) {
    this.message = message;
    this.pos = pos;
  }
  public String toString() {
    return pos.toString() + message;
  }
}
