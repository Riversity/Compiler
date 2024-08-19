package sema.util.error;

import sema.util.Position;

public class SyntaxError extends MyError {
  public SyntaxError(String msg, Position pos) {
    super("Syntax Error: " + msg, pos);
  }

  @Override
  public String printError() {
    return "Syntax Error";
  }
}
