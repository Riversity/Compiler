package util.error;

import util.Position;

public class UndefinedIdentifier extends MyError {
  public UndefinedIdentifier(String msg, Position pos) {
    super("Undefined Identifier: " + msg, pos);
  }

  @Override
  public String PrintError() {
    return "Undefined Identifier";
  }
}
