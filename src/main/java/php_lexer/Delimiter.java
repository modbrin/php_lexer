package php_lexer;

public class Delimiter extends Token {
    Delimiter(String value){
        super(value);
    }

    public static boolean match(String value) {
        return value.matches(DELIMITER_REGEX);
    }

    private static String DELIMITER_REGEX = "(\\(|\\)|\\{|\\}|\\[|\\]|;|<\\?php|\\?>|\\s)";
}
