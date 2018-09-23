package php_lexer;

public class Literal extends Token {
    Literal(String value) {
        super(value);
    }

    @Override
    public String typeString() {
        return "LITERAL";
    }

    public static boolean match(String value) {
        return value.matches(STRING_LITERAL) || value.matches(NUMERIC_LITERAL) || value.matches(BOOL_LITERAL)
                || value.matches(HEREDOC);
    }

    private static String STRING_LITERAL = "(\\\"(\\\\.|[^\"\\\\])*\\\")|(\\'(\\\\.|[^'\\\\])*\\')";
    private static String HEREDOC = "(?s)<<<.*?;";

    private static String LNUM = "[0-9]+";
    private static String DNUM = "(([0-9]*[\\.]LNUM)|(LNUM[\\.][0-9]*))"
            .replaceAll("LNUM", LNUM);
    private static String EXPONENT_DNUM = "[+-]?((LNUM|DNUM) [eE][+-]? LNUM)"
            .replaceAll("LNUM", LNUM)
            .replaceAll("DNUM", DNUM);
    private static String FLOATING = "(LNUM|DNUM|EXPONENT_DNUM)"
            .replace("LNUM",LNUM)
            .replace("DNUM",DNUM)
            .replace("EXPONENT_DNUM", EXPONENT_DNUM);

    private static String DECIMAL = "([1-9][0-9]*|0)";
    private static String HEXADECIMAL = "0[xX][0-9a-fA-F]+";
    private static String OCTAL = "0[0-7]+";
    private static String BINARY = "0[bB][01]+";
    private static String INTEGER = "(DECIMAL|HEXADECIMAL|OCTAL|BINARY)"
            .replace("DECIMAL", DECIMAL)
            .replace("HEXADECIMAL", HEXADECIMAL)
            .replace("OCTAL", OCTAL)
            .replace("BINARY", BINARY);

    private static String NUMERIC_LITERAL = "(INTEGER|FLOATING)"
            .replace("INTEGER",INTEGER)
            .replace("FLOATING",FLOATING);

    private static String BOOL_LITERAL = "(true|false|null)";
}
