package sema.util;

import org.antlr.v4.runtime.Token;

public class Position {
  private int row, column;
  public Position(Token token) {
    this.row = token.getLine();
    this.column = token.getCharPositionInLine();
  }
  public static Position blankPos = new Position(0, 0);
  public Position(int row, int column) {
    this.row = row;
    this.column = column;
  }
  public String toString() {
    return "On Line " + row + ", Column " + column + ": ";
  }
}
