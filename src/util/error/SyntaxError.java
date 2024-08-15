package util.error;

import util.Position;

public class SyntaxError extends MyError {
  public SyntaxError(String msg, Position pos) {
    super("Syntax Error: " + msg, pos);
  }

  @Override
  public String PrintError() {
    return "Syntax Error";
  }
}
