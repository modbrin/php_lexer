package php_lexer;

public class Delimiter extends Token {
    Delimiter(String value){
        super(value);
    }

    public static boolean match(String value) {
        return false;
    }
}
