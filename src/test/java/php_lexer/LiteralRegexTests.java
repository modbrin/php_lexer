package php_lexer;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class LiteralRegexTests {

    @Test
    public void literalsOneCommaShouldBeDetected() {
        assertTrue(Literal.match("\'privet\'"), "\'privet\'");
        assertTrue(Literal.match("\'privet \\\'TA\\\'\'"), "\'privet \\\'TA\\\'\'");
        assertTrue(Literal.match("\'\"\"\'"), "\'\"\"\'");
    }

    @Test
    public void literalsTwoCommasShouldBeDetected() {
        assertTrue(Literal.match("\"privet\""), "\"privet\"");
        assertTrue(Literal.match("\"privet \\\"TA\\\"\""), "\"privet \\\"TA\\\"\"");
        assertTrue(Literal.match("\"\'\'\""),"\"\'\'\"");
    }
}
