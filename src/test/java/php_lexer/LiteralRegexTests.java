package php_lexer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class LiteralRegexTests {

    @Test
    public void functionLiteralsShouldBeDetected() {
        assertTrue(Literal.match("list_all_users"), "\"list_all_users\"");
        assertTrue(Literal.match("tally"), "\"tally\"");
        assertTrue(Literal.match("deleteTclFiles"), "\"deleteTclFiles\"");
        assertTrue(Literal.match("LOWERCASE_IS_FOR_WIMPS"), "\"LOWERCASE_IS_FOR_WIMPS\"");
        assertTrue(Literal.match("_hide"), "\"_hide\"");
    }

    @Test
    public void  variableLiteralsShouldBeDetected() {
        assertTrue(Literal.match("$bill"), "\"$bill\"");
        assertTrue(Literal.match("$head_count"), "\"$head_count\"");
        assertTrue(Literal.match("$MaximumForce"), "\"$MaximumForce\"");
        assertTrue(Literal.match("$I_HEART_PHP"), "\"$I_HEART_PHP\"");
        assertTrue(Literal.match("$_underscore"), "\"$_underscore\"");
        assertTrue(Literal.match("$_int"), "\"$_int\"");
    }

    @Test
    public void  nonLiteralsShouldNotBeDetected() {
        assertFalse(Literal.match("1kek"), "\"1kek\"");
        assertFalse(Literal.match("++"), "\"++\"");
    }
}
