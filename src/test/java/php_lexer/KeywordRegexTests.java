package php_lexer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class KeywordRegexTests {

    @Test
    public void keywordsShouldBeDetected() {
        assertTrue(Keyword.match("__halt_compiler"), "\"__halt_compiler\" keyword");
        assertTrue(Keyword.match("break"), "\"break\" keyword");
        assertTrue(Keyword.match("clone"), "\"clone\" keyword");
        assertTrue(Keyword.match("die"), "\"die()\" keyword");
        assertTrue(Keyword.match("empty"), "\"empty()\" keyword");
        assertTrue(Keyword.match("endswitch"), "\"endswitch\" keyword");
        assertTrue(Keyword.match("final"), "\"final\" keyword");
        assertTrue(Keyword.match("global"), "\"global\" keyword");
        assertTrue(Keyword.match("include_once"), "\"include_once\" keyword");
        assertTrue(Keyword.match("list"), "\"list\" keyword");
        assertTrue(Keyword.match("private"), "\"private\" keyword");
        assertTrue(Keyword.match("return"), "\"return\" keyword");
        assertTrue(Keyword.match("try"), "\"try\" keyword");
        assertTrue(Keyword.match("xor"), "\"xor\" keyword");
        assertTrue(Keyword.match("abstract"), "\"abstract\" keyword");
        assertTrue(Keyword.match("callable"), "\"callable\" keyword");
        assertTrue(Keyword.match("const"), "\"const\" keyword");
        assertTrue(Keyword.match("do"), "\"do\" keyword");
        assertTrue(Keyword.match("enddeclare"), "\"enddeclare\" keyword");
        assertTrue(Keyword.match("endwhile"), "\"endwhile\" keyword");
        assertTrue(Keyword.match("finally"), "\"finally\" keyword");
        assertTrue(Keyword.match("goto"), "\"goto\" keyword");
        assertTrue(Keyword.match("instanceof"), "\"instanceof\" keyword");
        assertTrue(Keyword.match("namespace"), "\"namespace\" keyword");
        assertTrue(Keyword.match("protected"), "\"protected\" keyword");
        assertTrue(Keyword.match("static"), "\"static\" keyword");
        assertTrue(Keyword.match("unset"), "\"unset\" keyword");
        assertTrue(Keyword.match("yield"), "\"yield\" keyword");
        assertTrue(Keyword.match("case"), "\"case\" keyword");
        assertTrue(Keyword.match("continue"), "\"continue\" keyword");
        assertTrue(Keyword.match("echo"), "\"echo\" keyword");
        assertTrue(Keyword.match("endfor"), "\"endfor\" keyword");
        assertTrue(Keyword.match("eval"), "\"eval\" keyword");
        assertTrue(Keyword.match("for"), "\"for\" keyword");
        assertTrue(Keyword.match("if"), "\"if\" keyword");
        assertTrue(Keyword.match("insteadof"), "\"insteadof\" keyword");
        assertTrue(Keyword.match("new"), "\"new\" keyword");
        assertTrue(Keyword.match("public"), "\"public\" keyword");
        assertTrue(Keyword.match("switch"), "\"switch\" keyword");
        assertTrue(Keyword.match("use"), "\"use\" keyword");
        assertTrue(Keyword.match("array"), "\"array\" keyword");
        assertTrue(Keyword.match("catch"), "\"catch\" keyword");
        assertTrue(Keyword.match("declare"), "\"declare\" keyword");
        assertTrue(Keyword.match("else"), "\"else\" keyword");
        assertTrue(Keyword.match("endforeach"), "\"endforeach\" keyword");
        assertTrue(Keyword.match("exit"), "\"exit\" keyword");
        assertTrue(Keyword.match("foreach"), "\"foreach\" keyword");
        assertTrue(Keyword.match("implements"), "\"implements\" keyword");
        assertTrue(Keyword.match("interface"), "\"interface\" keyword");
        assertTrue(Keyword.match("or"), "\"or\" keyword");
        assertTrue(Keyword.match("require"), "\"require\" keyword");
        assertTrue(Keyword.match("throw"), "\"throw\" keyword");
        assertTrue(Keyword.match("var"), "\"var\" keyword");
        assertTrue(Keyword.match("as"), "\"as\" keyword");
        assertTrue(Keyword.match("class"), "\"class\" keyword");
        assertTrue(Keyword.match("default"), "\"default\" keyword");
        assertTrue(Keyword.match("elseif"), "\"elseif\" keyword");
        assertTrue(Keyword.match("endif"), "\"endif\" keyword");
        assertTrue(Keyword.match("extends"), "\"extends\" keyword");
        assertTrue(Keyword.match("function"), "\"function\" keyword");
        assertTrue(Keyword.match("include"), "\"include\" keyword");
        assertTrue(Keyword.match("isset"), "\"isset\" keyword");
        assertTrue(Keyword.match("print"), "\"print\" keyword");
        assertTrue(Keyword.match("require_once"), "\"require_once\" keyword");
        assertTrue(Keyword.match("trait"), "\"trait\" keyword");
        assertTrue(Keyword.match("while"), "\"while\" keyword");
    }

    @Test
    public void keywordsShouldBeDetectedDespiteWriting() {
        assertTrue(Keyword.match("trait"), "\"trait\" keyword");
        assertTrue(Keyword.match("tRaIt"), "\"tRaIt\" keyword");
        assertTrue(Keyword.match("Trait"), "\"Trait\" keyword");
        assertFalse(Keyword.match("tait"), "\"tait\"  not keyword");
    }
}
