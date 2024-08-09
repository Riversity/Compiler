package util;

public class Position {
  private int row, column;
  public Position(int row, int column) {
    this.row = row;
    this.column = column;
  }
  public String toString() {
    return "On Line " + row + ", Column " + column + ": ";
  }
}
