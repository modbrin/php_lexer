package php_lexer;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class OperatorRegexTests {
    @Test
    public void operatorsShouldBeDetected() {
        assertTrue(Operator.match("+"), "\"+\"");
        assertTrue(Operator.match("-"), "\"-\"");
        assertTrue(Operator.match("*"), "\"*\"");
        assertTrue(Operator.match("/"), "\"/\"");
        assertTrue(Operator.match("%"), "\"%\"");
        assertTrue(Operator.match("**"), "\"**\"");
        assertTrue(Operator.match("="), "\"=\"");
        assertTrue(Operator.match("+="), "\"+=\"");
        assertTrue(Operator.match("-="), "\"-=\"");
        assertTrue(Operator.match("*="), "\"*=\"");
        assertTrue(Operator.match("/="), "\"/=\"");
        assertTrue(Operator.match("%="), "\"%=\"");
        assertTrue(Operator.match("=="), "\"==\"");
        assertTrue(Operator.match("==="), "\"===\"");
        assertTrue(Operator.match("!="), "\"!=\"");
        assertTrue(Operator.match("<>"), "\"<>\"");
        assertTrue(Operator.match("!=="), "\"!==\"");
        assertTrue(Operator.match(">"), "\">\"");
        assertTrue(Operator.match("<"), "\"<\"");
        assertTrue(Operator.match(">="), "\">=\"");
        assertTrue(Operator.match("<="), "\"<=\"");
        assertTrue(Operator.match("++"), "\"++\"");
        assertTrue(Operator.match("--"), "\"--\"");
        assertTrue(Operator.match("&&"), "\"&&\"");
        assertTrue(Operator.match("||"), "\"||\"");
        assertTrue(Operator.match("!"), "\"!\"");
        assertTrue(Operator.match("."), "\".\"");
        assertTrue(Operator.match(".="), "\".=\"");
        assertTrue(Operator.match("^"), "\"^\"");
        assertTrue(Operator.match("~"), "\"~\"");
        assertTrue(Operator.match("<<"), "\"<<\"");
        assertTrue(!Operator.match("<<<"), "\"<<<\"");
        assertTrue(Operator.match("->"), "\"->\"");
        assertTrue(Operator.match(">>"), "\">>\"");
        assertTrue(Operator.match("=&"), "\"=&\"");
    }
}
