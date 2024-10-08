package sema.util.error;

import sema.util.Position;

public class MultipleDefinitions extends MyError {
  public MultipleDefinitions(String msg, Position pos) {
    super("Multiple Definitions: " + msg, pos);
  }

  @Override
  public String printError() {
    return "Multiple Definitions";
  }
}
