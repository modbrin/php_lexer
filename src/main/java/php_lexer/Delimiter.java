package php_lexer;

public class Delimiter extends Token {
    Delimiter(String value){
        super(value);
    }

    @Override
    public String typeString() {
        return "DELIMITER";
    }

    public static boolean match(String value) {
        return value.matches(DELIM_REGULAR);
    }

    private static String DELIM_REGULAR = "(\\(|\\)|\\{|\\}|\\[|\\]|;"+
            "|<\\?php|\\?>|<\\?|<%|<\\?=|<%=|\\s*|,|\\\\)";
}
