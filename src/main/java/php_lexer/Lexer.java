package php_lexer;

import java.io.*;

public class Lexer {
    private BufferedReader reader;
    private char c;
    private String buffer;
    private boolean isEnd;
    private boolean isLookaheadEnd;

    private char[] lookahead;
    private static final int lookaheadLength = 4;

    public Lexer(File file) throws IOException {
        this.reader = new BufferedReader(new FileReader(file));
        buffer = "";
        isEnd = false;
        isLookaheadEnd = false;
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
    private String lookaheadString(int N) {
        StringBuilder result = new StringBuilder();
        int bound = (N < lookaheadLength) ? N : lookaheadLength;
        for (int i = 0; i < bound; ++i) {
            result.append(lookahead[i]);
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

    /**
     * Moves lookahead buffer by one character
     */
    private void moveLookahead() throws IOException {
        move();
        pushBack(current());
        if(lookahead[0]==(char)-1){
            isLookaheadEnd = true;
        }
    }

    /**
     * Shifts lookahead buffer by n amount characters
     *
     * @param shiftAmount amount of characters to shift
     */
    private void shiftLookahead(int shiftAmount) throws IOException {
        for (int i = 0; i < shiftAmount; ++i) {
            moveLookahead();
        }
    }

    /**
     * Returns current character
     */
    private char current() {
        return c;
    }

    /**
     * Moves current character
     */
    private void move() throws IOException {
        if (isEnd) {
            c = (char) -1;
            return;
        }
        int current = reader.read();
        if (current == -1) {
            isEnd = true;
            c = (char) -1;
        } else {
            c = (char) current;
        }
    }

    /**
     * Returns lexical token in the stream
     *
     * @return token
     */
    public Token nextToken() throws IOException {
        Token toReturn = null;
        buffer = "";
        while (true) {
            if (isLookaheadEnd) {
                return new Error(buffer);
            }
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
                if (buffer.concat(lookaheadString(1)).toLowerCase()
                        .matches("<\\?|\\?>|\\/\\/|\\*\\/|\\/\\*")) {
                    continue;
                } else if (buffer.concat(lookaheadString(2)).toLowerCase()
                        .matches("<<<")) {
                    buffer += Character.toString(head());
                    moveLookahead();
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


    /**
     * Checks if there is a nextToken in the stream
     *
     * @return true if there is next token
     */
    public boolean hasNextToken() {
        return !isLookaheadEnd;
    }


}
