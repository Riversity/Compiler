package util.error;

import org.antlr.v4.runtime.*;

public class TroubleMaker extends BaseErrorListener {
  @Override
  public void syntaxError(Recognizer<?, ?> recognizer,
                          Object offendingSymbol,
                          int line, int charPositionInLine,
                          String msg,
                          RecognitionException e) {
    throw new SyntaxError(msg, line, charPositionInLine);
  }
}
