package php_lexer;

public class Literal extends Token {
    Literal(String value){
        super(value);
    }

    public static boolean match(String value) {
        return false;
    }
}
