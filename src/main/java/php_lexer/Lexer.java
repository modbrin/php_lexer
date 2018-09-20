package php_lexer;

import java.io.*;

public class Lexer {
    private BufferedReader reader;
    private char c;
    private String buffer;
    private boolean isEnd;

    public Lexer(File file) throws IOException {
        this.reader = new BufferedReader(new FileReader(file));
        buffer = "";
        isEnd = false;
        move();
    }

    private char current() {
        return c;
    }

    private void move() throws IOException {
        int current = reader.read();
        if(current == -1) {
            isEnd = true;
        } else {
            c = (char) current;
        }
    }

    public Token nextToken() throws IOException {
        buffer = "";
        while(true) {
            buffer += Character.toString(current());
            if (Identifier.match(buffer)) {
                do{
                    move();
                    buffer += Character.toString(current());
                } while(Identifier.match(buffer));
                return new Identifier(buffer);
            } else if (Operator.match(buffer)) {

            } else if (Literal.match(buffer)) {

            } else if (Delimiter.match(buffer)) {

            }
            move();
        }

    }

    public boolean hasNextToken() {
        return false;
    }


}
