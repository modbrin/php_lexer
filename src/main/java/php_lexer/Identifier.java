package php_lexer;

public class Identifier extends Token {
    Identifier(String value){
        super(value);
    }

    public static boolean match(String value) {
        return false;
    }
}
