package sema.util.error;

import sema.util.Position;

public class DimensionOutOfBound extends MyError {
  public DimensionOutOfBound(String msg, Position pos) {
    super("Dimension Out Of Bound: " + msg, pos);
  }

  @Override
  public String printError() {
    return "Dimension Out Of Bound";
  }
}
