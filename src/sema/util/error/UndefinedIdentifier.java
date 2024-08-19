package sema.util.error;

import sema.util.Position;

public class UndefinedIdentifier extends MyError {
  public UndefinedIdentifier(String msg, Position pos) {
    super("Undefined Identifier: " + msg, pos);
  }

  @Override
  public String printError() {
    return "Undefined Identifier";
  }
}
