import ast.node.BaseNode;
import org.antlr.v4.runtime.*;
import parser.*;
import java.util.stream.Stream;

public class Compiler {
  public static void main(String[] args) {
    /* Stream.iterate(1, i -> i + 1)
        .limit(10)
        .forEach(System.out::println); */
    if (args.length == 0) {
      System.out.println("Usage: java Compiler <filename>");
      return;
    } else if (args[0].equals("-fsyntax-only")) {
        System.out.println("Semantic check");
    } else if (args[0].equals("-S")) {
        System.out.println("Generate assembly code");
        return;
    } else {
        System.out.println("Unknown option");
        return;
    }
    try {
      var input = CharStreams.fromStream(System.in);
      MxLexer lexer = new MxLexer(input);
      lexer.removeErrorListeners();
      lexer.addErrorListener(new BaseErrorListener());
      CommonTokenStream tokens = new CommonTokenStream(lexer);
      MxParser parser = new MxParser(tokens);
      parser.removeErrorListeners();
      parser.addErrorListener(new BaseErrorListener());
      // BaseNode program = new ASTBuilder().visit(parser.program());
      // symbol collector
      // type checker
    } catch (Exception e) {
      System.out.println(e.toString());
      System.exit(1);
    } finally {
      System.out.println("Compile successfully");
      System.exit(0);
    }
  }
}