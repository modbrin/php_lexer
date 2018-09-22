package php_lexer;

public class Identifier extends Token {
    Identifier(String value) {
        super(value);
    }

    @Override
    public String typeString() {
        return "IDENTIFIER";
    }

    public static boolean match(String value) {
        return value.toLowerCase().matches(ID_REG);
    }

    /**
     * LETTER = [a-zA-Z]
     * DIGIT = [0-9]
     * EXT_ASCII = [\\xA8-\\xFE]
     *
     * IDENTIFIER_NAME = (LETTER|_)(LETTER|DIGIT|_|EXT_ASCII)*
     * VARIABLE_DECLARATION = \\$(\\$)?IDENTIFIER_NAME
     *
     * IDENTIFIER_REGEXP = (IDENTIFIER_NAME|VARIABLE_DECLARATION)
     */
    private static String ID_REG = "(([a-zA-Z]|_)([a-zA-Z]|[0-9]|_|[\\xA8-\\xFE])"+
            "*|\\$(\\$)?([a-zA-Z]|_)([a-zA-Z]|[0-9]|_|[\\xA8-\\xFE])*)";
}
