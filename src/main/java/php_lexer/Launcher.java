package php_lexer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Launcher {
  public static void main(String[] args) throws IOException {

    Lexer lex = new Lexer(new File("src/main/input/test6.php"));
    FileWriter writer = new FileWriter(new File("out.txt"));

    while (lex.hasNextToken()) {
      Token current = lex.nextToken();
      writer.write(String.format("<%s: \'%s\'>\n",current.typeString(),current.toString()));
    }
    writer.close();
  }
}
