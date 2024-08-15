package util.error;

import org.antlr.v4.runtime.*;
import util.Position;

public class TroubleMaker extends BaseErrorListener {
  @Override
  public void syntaxError(Recognizer<?, ?> recognizer,
                          Object offendingSymbol,
                          int line, int charPositionInLine,
                          String msg,
                          RecognitionException e) {
    throw new InvalidIdentifier(msg, new Position(line, charPositionInLine));
  }
}
