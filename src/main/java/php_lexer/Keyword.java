package php_lexer;

public class Keyword extends Token {
    Keyword(String value){
        super(value);
    }

    public static boolean match(String value) {
        return value.toLowerCase().matches(KEYWORD_REGEXP);
    }

    private static String KEYWORD_REGEXP = "(" +
            "(a(bstract|nd|rray|s))|" +
            "(c(a(llable|se|tch)|l(ass|one)|on(st|tinue)))|" +
            "(d(e(clare|fault)|ie|o))|" +
            "(e(cho|lse(if)?|mpty|nd(declare|for(each)?|if|switch|while)|val|x(it|tends)))|" +
            "(f(inal(ly)?|or(each)?|unction))|" +
            "(g(lobal|oto))|" +
            "(i(f|mplements|n(clude(_once)?|st(anceof|eadof)|terface)|sset))|" +
            "(n(amespace|ew))|" +
            "(p(r(i(nt|vate)|otected)|ublic))|" +
            "(re(quire(_once)?|turn))|" +
            "(s(tatic|witch))|" +
            "(t(hrow|r(ait|y)))|" +
            "(u(nset|se))|" +
            "(__halt_compiler|break|list|(x)?or|var|while)|" +
            "(yield)" +
            ")";
}
