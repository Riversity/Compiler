package util.error;

import org.antlr.v4.runtime.*;

public class SyntaxError extends RuntimeException {
  private String message;
  private int line;
  private int charPositionInLine;
  public SyntaxError(String message, int line, int charPositionInLine) {
    this.message = message;
    this.line = line;
    this.charPositionInLine = charPositionInLine;
  }
  public String toString() {
    return message + Integer.toString(line) + Integer.toString(charPositionInLine);
  }
}
