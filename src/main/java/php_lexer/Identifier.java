package php_lexer;

public class Identifier extends Token {
    Identifier(String value){
        super(value);
    }

    public static boolean match(String value) {

        return value.toLowerCase().matches(IDENTIFIER_REGEXP);
    }

    private static String LETTER = "[a-zA-z]";
    private static String DIGIT = "[0-9]";
    private static String EXT_ASCII = "[\\xA8-\\xFE]";

    private static String IDENTIFIER_NAME = "(LETTER|_)(LETTER|DIGIT|_|EXT_ASCII)*"
            .replaceAll("LETTER",LETTER)
            .replaceAll("DIGIT", DIGIT)
            .replaceAll("EXT_ASCII",EXT_ASCII);

    private static String VARIABLE_DECLARATION = "\\$(\\$)?IDENTIFIER_NAME"
            .replace("IDENTIFIER_NAME", IDENTIFIER_NAME);

    private static String IDENTIFIER_REGEXP = "(IDENTIFIER_NAME|VARIABLE_DECLARATION)"
            .replace("IDENTIFIER_NAME",IDENTIFIER_NAME)
            .replace("VARIABLE_DECLARATION", VARIABLE_DECLARATION);
}