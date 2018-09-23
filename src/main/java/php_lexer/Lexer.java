package php_lexer;

import java.io.*;

public class Lexer {
    // class data containers
    private BufferedReader reader;
    private char c;
    private String buffer;
    private boolean isEnd;
    private boolean isLookaheadEnd;
    private char[] lookahead;
    private static final int lookaheadLength = 4;

    /**
     * PHP Lexer that produces token stream from given file.
     *
     * @param file .php file to be tokenized
     * @throws IOException
     */
    public Lexer(File file) throws IOException {
        this.reader = new BufferedReader(new FileReader(file));
        buffer = "";
        isEnd = false;
        isLookaheadEnd = false;
        lookahead = new char[lookaheadLength];
        shiftLookahead(lookaheadLength);
    }

    /**
     * Get first char from lookahead
     */
    private char head() {
        return lookahead[0];
    }

    /**
     * Get N first chars from lookahead array,
     * if N is greater than lookahead size then length is limited by
     * lookahead size.
     *
     * @return String consisting of N first characters from lookahead
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
     * Append one symbol to the back of lookahead, shift all lookahead by one, first symbol value is dumped.
     *
     * @param newChar Symbol to append
     */
    private void pushBack(char newChar) {
        int lastIndex = lookaheadLength - 1;
        for (int i = 1; i <= lastIndex; ++i) {
            lookahead[i - 1] = lookahead[i];
        }
        lookahead[lastIndex] = newChar;
    }

    /**
     * Moves lookahead buffer by one character using chars from internal FileReader
     */
    private void moveLookahead() throws IOException {
        move();
        pushBack(current());
        if (lookahead[0] == (char) -1) {
            isLookaheadEnd = true;
        }
    }

    /**
     * Shifts lookahead buffer by 'shiftAmount' characters
     *
     * @param shiftAmount number of characters to shift
     */
    private void shiftLookahead(int shiftAmount) throws IOException {
        for (int i = 0; i < shiftAmount; ++i) {
            moveLookahead();
        }
    }

    /**
     * Returns current character from pointer in file
     */
    private char current() {
        return c;
    }

    /**
     * Moves current character pointer in file
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
     * @return token Child of Token class
     */
    public Token nextToken() throws IOException {
        Token toReturn = null;
        buffer = "";
        while (true) {
            //check if no more chars to read
            if (isLookaheadEnd) {
                return new Error(buffer);
            }
            buffer += Character.toString(head());
            moveLookahead();

            if (Delimiter.match(buffer)) {
                // try to detect a Dilimiter in buffer
                // collect Delimiter while it matches it's pattern
                while (Delimiter.match(buffer + head())) {
                    if(isLookaheadEnd) {
                        break;
                    }
                    buffer += Character.toString(head());
                    moveLookahead();
                }
                toReturn = new Delimiter(buffer);
            } else if (Operator.match(buffer)) {
                // try to detect an Operator in buffer

                String res = buffer.concat(lookaheadString(1));
                // handle cases when delimiter starts as operator
                if (buffer.concat(lookaheadString(1)).toLowerCase()
                        .matches("<\\?|\\?>|\\/\\/|\\*\\/|\\/\\*")) {
                    continue;
                } else if (buffer.concat(lookaheadString(2)).toLowerCase()
                        .matches("<<<")) {
                    buffer += Character.toString(head());
                    moveLookahead();
                    continue;
                } else {
                    // collect Operator while it matches it's pattern
                    while (Operator.match(buffer + head())) {
                        if(isLookaheadEnd) {
                            break;
                        }
                        buffer += Character.toString(head());
                        moveLookahead();
                    }
                    toReturn = new Operator(buffer);
                }
            } else if (Literal.match(buffer)) {
                // try to detect a Literal in buffer
                // collect Literal while it matches it's pattern
                while (Literal.match(buffer + head())) {
                    if(isLookaheadEnd) {
                        break;
                    }
                    buffer += Character.toString(head());
                    moveLookahead();
                }
                toReturn = new Literal(buffer);
            } else if (Identifier.match(buffer)) {
                // try to detect an Identifier in buffer
                // collect Identifier while it matches it's pattern
                while (Identifier.match(buffer + head())) {
                    if(isLookaheadEnd) {
                        break;
                    }
                    buffer += Character.toString(head());
                    moveLookahead();
                }
                toReturn = (Keyword.match(buffer)) ? new Keyword(buffer) : new Identifier(buffer);
            } else if (Comment.match(buffer)) {
                // try to detect a Comment in buffer
                // collect Comment while it matches it's pattern
                while (Comment.match(buffer + head())) {
                    if(isLookaheadEnd) {
                        break;
                    }
                    buffer += Character.toString(head());
                    moveLookahead();
                }
                toReturn = new Comment(buffer);
            }
            // if there was a correct detection, return the token
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
