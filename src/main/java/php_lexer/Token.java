package php_lexer;

public abstract class Token {

    private String value;

    public Token(String value) {
        this.value = value;
    }

    public abstract String typeString();
    @Override
    public String toString() {
        return value.replaceAll("(\\r|\\n|\\r\\n)+", "\\\\n");
    }
}
