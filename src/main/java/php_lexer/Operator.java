package php_lexer;

public class Operator extends Token {
    Operator(String value) {
        super(value);
    }

    @Override
    public String typeString() {
        return "OPERATOR";
    }

    public static boolean match(String value) {
        return value.matches(OPERATOR_REGEXP);
    }

    private static String OPERATOR_REGEXP = "(\\+)|(-)|(\\*)|(\\/)|(=)|(=&)|(&)" +
            "|(\\|)|(\\^)|(~)|(<<)|(>>)|(==)|(===)|(!=)|(<>)|(!==)|(<)|(<=)|(>=)" +
            "|(\\+\\+)|(--)|(&&)|(\\|\\|)|(!)|(->)|(<<<)|(%)|(\\*\\*)|(\\+=)|(-=)" +
            "|(\\*=)|(\\.=)|(/=)|(%=)|(>)|(\\.)|(::)|(:)";
}
