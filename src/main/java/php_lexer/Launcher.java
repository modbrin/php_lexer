package php_lexer;

import java.io.File;
import java.io.IOException;

public class Launcher {
  public static void main(String[] args) throws IOException {

    Lexer lex = new Lexer(new File("src/main/input/input.php"));

    while (lex.hasNextToken()) {
      Token current = lex.nextToken();
      System.out.format("%s: %s\n",current.typeString(),current.toString());

    }
  }
}
