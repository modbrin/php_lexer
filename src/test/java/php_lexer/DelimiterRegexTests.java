package php_lexer;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DelimiterRegexTests {

    @Test
    public void newlineShouldBeDetected() {
        assertTrue(Delimiter.match("\n"), "\n");
        assertTrue(Delimiter.match("\r\n"), "\r\n");
        assertTrue(Delimiter.match("\r"), "\r");
        assertTrue(Delimiter.match("         \n"), "         \n");
    }

    @Test
    public void semicolomnShouldBeDetected() {
        assertTrue(Delimiter.match(";"), ";");
    }

    @Test
    public void openTagShouldBeDetected() {
        assertTrue(Delimiter.match("<?php"), "<?php");
        assertTrue(Delimiter.match("<?="), "<?=");
    }

    @Test
    public void closeTagShoulBeDetected() {
        assertTrue(Delimiter.match("?>"));
    }
}
