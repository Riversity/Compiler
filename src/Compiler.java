import ast.AstBuilder;
import ast.node.*;
import org.antlr.v4.runtime.*;
import parser.*;
import sema.SymbolCollector;
import util.error.MyError;
import util.error.TroubleMaker;

public class Compiler {
  public static void main(String[] args) {
    /* Stream.iterate(1, i -> i + 1)
        .limit(10)
        .forEach(System.out::println); */
    if (args.length == 0) {
      System.err.println("Usage: -fsyntax-only <filename>");
      return;
    } else if (args[0].equals("-fsyntax-only")) {
        System.err.println("Semantic check");
    } else if (args[0].equals("-S")) {
        System.err.println("Generate assembly code");
        return;
    } else {
        System.err.println("Unknown option");
        return;
    }
    try {
      var input = CharStreams.fromStream(System.in);
      MxLexer lexer = new MxLexer(input);
      lexer.removeErrorListeners();
      lexer.addErrorListener(new TroubleMaker());
      CommonTokenStream tokens = new CommonTokenStream(lexer);
      MxParser parser = new MxParser(tokens);
      parser.removeErrorListeners();
      parser.addErrorListener(new TroubleMaker());
      BaseNode program = new AstBuilder().visit(parser.program());
      SymbolCollector collector = new SymbolCollector();
      collector.visit((Program) program);
      // BaseNode program = new ASTBuilder().visit(parser.program());
      // symbol collector
      // type checker
    } catch (Exception e) {
      System.err.println(e.toString());
      if(e instanceof MyError) {
        System.out.println(((MyError) e).printError());
      }
      System.exit(1);
    } finally {
      System.err.println("Compile successfully");
      System.exit(0);
    }
  }
}