package php_lexer;

public class Comment extends Token {
    Comment(String value){
        super(value);
    }

    @Override
    public String typeString() {
        return "COMMENT";
    }

    public static boolean match(String value) {
        return value.matches(COM_ALL);
    }
    private static String COM_HASH = "#.*";
    private static String COM_C_SINGLE = "\\/\\/.*";
    private static String COM_C_MULTI = "(?s)/\\*.*?\\*/";
    private static String COM_C_DOC = "(?s)/\\*\\*.*?\\*/";
    private static String COM_HEREDOC = "(?s)<<<.*?;";
    private static String COM_ALL = String.format("(%s|%s|%s|%s|%s)",
            COM_HASH, COM_C_SINGLE, COM_C_MULTI, COM_C_DOC, COM_HEREDOC);
}
