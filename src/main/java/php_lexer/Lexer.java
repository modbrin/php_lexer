package php_lexer;

import java.io.*;

public class Lexer {
    private BufferedReader reader;
    private char c;
    private String buffer;
    private boolean isEnd;

    private char[] lookahead;
    final static int lookaheadLength = 4;

    public Lexer(File file) throws IOException {
        this.reader = new BufferedReader(new FileReader(file));
        buffer = "";
        isEnd = false;
        lookahead = new char[lookaheadLength];
        shiftLookahead(lookaheadLength);
    }

    /**
     * Get first char from lookahead char in lookahead
     */
    private char head() {
        return lookahead[0];
    }

    /**
     * Get N first chars from lookahead array
     *
     * @return
     */
    public String lookaheadString(int N) {
        StringBuilder result = new StringBuilder();
        int bound = (N < lookaheadLength) ? N : lookaheadLength;
        for (int i = 0; i < bound; ++i) {
            result.append(Character.toString(lookahead[i]));
        }
        return result.toString();
    }

    /**
     * Append one symbol to back of lookahead, shift all lookahead by one, first symbol value is dumped.
     *
     * @param newChar symbol to append
     */
    private void pushBack(char newChar) {
        int lastIndex = lookaheadLength - 1;
        for (int i = 1; i <= lastIndex; ++i) {
            lookahead[i - 1] = lookahead[i];
        }
        lookahead[lastIndex] = newChar;
    }

    private void moveLookahead() throws IOException {
        move();
        pushBack(current());
    }

    private void shiftLookahead(int shiftAmount) throws IOException {
        for (int i = 0; i < shiftAmount; ++i) {
            moveLookahead();
        }
    }

    private char current() {
        return c;
    }

    private void move() throws IOException {
        if (isEnd) {
            c = Character.MIN_VALUE;
            return;
        }
        int current = reader.read();
        if (current == -1) {
            isEnd = true;
            c = Character.MIN_VALUE;
        } else {
            c = (char) current;
        }
    }

    public Token nextToken() throws IOException {
        Token toReturn = null;
        buffer = "";
        while (true) {
            buffer += Character.toString(head());
            moveLookahead();
            if (Delimiter.match(buffer)) {
                while (Delimiter.match(buffer + head())) {
                    buffer += Character.toString(head());
                    moveLookahead();
                }
                toReturn = new Delimiter(buffer);
            } else if (Operator.match(buffer)) {
                String res = buffer.concat(lookaheadString(1));
                if (buffer.concat(lookaheadString(1)).toLowerCase().matches("<\\?|\\?>|\\/\\/|\\*\\/|\\/\\*")) {
                    continue;
                } else {
                    while (Operator.match(buffer + head())) {
                        buffer += Character.toString(head());
                        moveLookahead();
                    }
                    toReturn = new Operator(buffer);
                }
            } else if (Literal.match(buffer)) {
                while (Literal.match(buffer + head())) {
                    buffer += Character.toString(head());
                    moveLookahead();
                }
                toReturn = new Literal(buffer);
            } else if (Identifier.match(buffer)) {
                while (Identifier.match(buffer + head())) {
                    buffer += Character.toString(head());
                    moveLookahead();
                }
                toReturn = (Keyword.match(buffer)) ? new Keyword(buffer) : new Identifier(buffer);
            } else if (Comment.match(buffer)) {
                while (Comment.match(buffer + head())) {
                    buffer += Character.toString(head());
                    moveLookahead();
                }
                toReturn = new Comment(buffer);
            }
            if (toReturn != null) {
                return toReturn;
            }
        }
    }

//    public Token parseDelimiter(String buffer) throws IOException {
//        while (Delimiter.match(buffer + head())) {
//            buffer += Character.toString(head());
//            moveLookahead();
//        }
//        toReturn = new Delimiter(buffer);
//    }
//
//    public static Token parseOperator() {
//
//    }
//
//    public static Token parseLiteral() {
//
//    }
//
//    public static Token parseIdentifier() {
//
//    }
//
//    public static Token parseComment() {
//
//    }


    public boolean hasNextToken() {
        return !isEnd;
    }


}
