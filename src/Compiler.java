import ast.AstBuilder;
import ast.node.*;
import org.antlr.v4.runtime.*;
import parser.*;
import sema.SemanticChecker;
import sema.SymbolCollector;
import util.error.MyError;
import util.error.TroubleMaker;

public class Compiler {
  public static void main(String[] args) {
    if (args.length == 0) {
      System.err.println("Usage: -fsyntax-only <filename>");
    } else if (args[0].equals("-fsyntax-only")) {
      System.err.println("Semantic check");
    } else if (args[0].equals("-S")) {
      System.err.println("Generate assembly code");
    } else {
      System.err.println("Unknown option");
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
      SemanticChecker checker = new SemanticChecker();
      checker.visit((Program) program);
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