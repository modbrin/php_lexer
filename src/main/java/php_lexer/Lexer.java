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
            move();
            if (Delimiter.match(buffer)) {
                while(Delimiter.match(buffer+current())) {
                    buffer += Character.toString(current());
                    move();
                }
                return new Delimiter(buffer);
            } else if (Operator.match(buffer)) {
                while(Operator.match(buffer+current())) {
                    buffer += Character.toString(current());
                    move();
                }
                return new Operator(buffer);
            } else if (Literal.match(buffer)) {
                while(Literal.match(buffer+current())) {
                    buffer += Character.toString(current());
                    move();
                }
            } else if (Identifier.match(buffer)) {
                while(Identifier.match(buffer+current())) {
                    buffer += Character.toString(current());
                    move();
                }
                return new Identifier(buffer);
            }

        }
    }

    public void skipToPHP() {

    }

    public boolean hasNextToken() {
        return !isEnd;
    }


}
