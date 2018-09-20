package php_lexer;

import java.io.File;
import java.io.IOException;

public class Launcher {
  public static void main(String[] args) throws IOException {

    Lexer lex = new Lexer(new File("src/main/input/input.php"));

    while (lex.hasNextToken()) {
      System.out.print(lex.nextToken().toString().trim() + " -> ");
    }
  }
}
