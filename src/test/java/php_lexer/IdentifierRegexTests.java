package php_lexer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IdentifierRegexTests {

    @Test
    public void functionIdentifiersShouldBeDetected() {
        assertTrue(Identifier.match("list_all_users"), "\"list_all_users\"");
        assertTrue(Identifier.match("tally"), "\"tally\"");
        assertTrue(Identifier.match("deleteTclFiles"), "\"deleteTclFiles\"");
        assertTrue(Identifier.match("LOWERCASE_IS_FOR_WIMPS"), "\"LOWERCASE_IS_FOR_WIMPS\"");
        assertTrue(Identifier.match("_hide"), "\"_hide\"");
    }

    @Test
    public void  variableIdentifiersShouldBeDetected() {
        assertTrue(Identifier.match("$bill"), "\"$bill\"");
        assertTrue(Identifier.match("$head_count"), "\"$head_count\"");
        assertTrue(Identifier.match("$MaximumForce"), "\"$MaximumForce\"");
        assertTrue(Identifier.match("$I_HEART_PHP"), "\"$I_HEART_PHP\"");
        assertTrue(Identifier.match("$_underscore"), "\"$_underscore\"");
        assertTrue(Identifier.match("$_int"), "\"$_int\"");
    }

    @Test
    public void  nonIdentifiersShouldNotBeDetected() {
        assertFalse(Identifier.match("1kek"), "\"1kek\"");
        assertFalse(Identifier.match("++"), "\"++\"");
    }
}
