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

//    private static String LETTER = "[a-zA-Z]";
//    private static String DIGIT = "[0-9]";
//    private static String EXT_ASCII = "[\\xA8-\\xFE]";
//
//    private static String IDENTIFIER_NAME = "(LETTER|_)(LETTER|DIGIT|_|EXT_ASCII)*"
//            .replaceAll("LETTER",LETTER)
//            .replaceAll("DIGIT", DIGIT)
//            .replaceAll("EXT_ASCII",EXT_ASCII);
//
//    private static String VARIABLE_DECLARATION = "\\$(\\$)?IDENTIFIER_NAME"
//            .replace("IDENTIFIER_NAME", IDENTIFIER_NAME);
//
//    private static String IDENTIFIER_REGEXP = "(IDENTIFIER_NAME|VARIABLE_DECLARATION)"
//            .replace("IDENTIFIER_NAME",IDENTIFIER_NAME)
//            .replace("VARIABLE_DECLARATION", VARIABLE_DECLARATION);

    private static String ID_REG = "(([a-zA-Z]|_)([a-zA-Z]|[0-9]|_|[\\xA8-\\xFE])"+
            "*|\\$(\\$)?([a-zA-Z]|_)([a-zA-Z]|[0-9]|_|[\\xA8-\\xFE])*)";
}
