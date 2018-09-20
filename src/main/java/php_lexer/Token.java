package php_lexer;

public abstract class Token {

    private String value;

    public Token(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
