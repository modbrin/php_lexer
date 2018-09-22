package php_lexer;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CommentRegexTest {

    @Test
    public void oneLineCommentsShouldBeDetected() {
        assertTrue(Comment.match("// THIS IS COMMENT IN ONE LINE"), "// THIS IS COMMENT IN ONE LINE");
        assertTrue(Comment.match("// THIS //IS COMMENT //IN ONE LINE"), "// THIS //IS COMMENT //IN ONE LINE");
    }

    @Test
    public void multilineCommentsShouldBeDetected() {
        assertTrue(Comment.match("/* THIS IS FAKE MULTILINE COMMENT */"), "/* THIS IS FAKE MULTILINE COMMENT */");
        assertTrue(Comment.match("/* THIS IS\n FAKE\n MULTILINE\n COMMENT */"), "/* THIS IS\\n FAKE\\n MULTILINE\\n COMMENT */");
    }

    @Test
    public void sharpCommentsShouldBeDetected() {
        assertTrue(Comment.match("# THIS IS SHARP COMMENT"), "# THIS IS SHARP COMMENT");
        assertTrue(Comment.match("# THIS IS # COMMENT"), "# THIS IS SHARP COMMENT");
    }
}
