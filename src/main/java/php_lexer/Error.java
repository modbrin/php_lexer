package php_lexer;

public class Error extends Token {
    Error(String value){
        super(value);
    }

    @Override
    public String typeString() {
        return "ERROR";
    }
}
