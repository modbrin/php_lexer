package php_lexer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Launching point of application, shows the example of usage for lexer.
 */
public class Launcher {
    public static void main(String[] args) throws IOException {

        // Create 'Lexer' for tokenization and 'FileWriter' for writing results into file.
        Lexer lex = new Lexer(new File("input.php"));
        FileWriter writer = new FileWriter(new File("out.txt"));

        // Get all tokens one-by-one.
        while (lex.hasNextToken()) {
            Token current = lex.nextToken();
            writer.write(String.format("<%s: \'%s\'>\n", current.typeString(), current.toString()));
        }
        writer.close();
    }
}
