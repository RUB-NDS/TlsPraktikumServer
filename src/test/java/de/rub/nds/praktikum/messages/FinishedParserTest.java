package de.rub.nds.praktikum.messages;

import de.rub.nds.praktikum.exception.ParserException;
import de.rub.nds.praktikum.util.Util;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 *
 */
@Category(de.rub.nds.praktikum.Aufgabe4.class)
public class FinishedParserTest {

    @Test
    public void testParse() {
        FinishedParser parser = new FinishedParser(Util.hexStringToByteArray("FFEEDDCCBBAA9988776655443322110000112233445566778899AABBCCDDEEFF"));
        Finished parsedFinished = parser.parse();
        assertArrayEquals("Finished is not VerifyData",Util.hexStringToByteArray("FFEEDDCCBBAA9988776655443322110000112233445566778899AABBCCDDEEFF"), parsedFinished.getVerifyData());
    }

    @Test(expected = ParserException.class)
    public void testParseWithGarbageDataLength() {
        FinishedParser parser = new FinishedParser(Util.hexStringToByteArray("FFEEDDCCBBAA9988776655443322110000112233445566778899AABBCCDDEEFF00"));
        parser.parse();
    }

}
