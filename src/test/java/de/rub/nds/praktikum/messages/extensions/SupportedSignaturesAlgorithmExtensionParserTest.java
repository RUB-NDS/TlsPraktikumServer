package de.rub.nds.praktikum.messages.extensions;

import de.rub.nds.praktikum.constants.SignatureAndHashAlgorithm;
import de.rub.nds.praktikum.exception.ParserException;
import de.rub.nds.praktikum.util.Util;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 *
 */
@Category(de.rub.nds.praktikum.Aufgabe2.class)
public class SupportedSignaturesAlgorithmExtensionParserTest {

    @Test
    public void testParseValid() {
        SupportedSignaturesAlgorithmExtensionParser parser = new SupportedSignaturesAlgorithmExtensionParser(Util.hexStringToByteArray("001E060106020603050105020503040104020403030103020303020102020203"));
        SupportedSignaturesAlgorithmExtension extension = parser.parse();
        assertEquals("There is exactly 15 enties in the list", 15, extension.getSignatureAlgorithmsList().size());
        assertTrue("The list contains ecdsa sha256", extension.getSignatureAlgorithmsList().contains(SignatureAndHashAlgorithm.ECDSA_SHA256));
    }

    @Test(expected = ParserException.class)
    public void testParseInvalidLength() {
        SupportedSignaturesAlgorithmExtensionParser parser = new SupportedSignaturesAlgorithmExtensionParser(Util.hexStringToByteArray("0004001D"));
        parser.parse();
    }

    @Test(expected = ParserException.class)
    public void testParseUnevenLength() {
        SupportedSignaturesAlgorithmExtensionParser parser = new SupportedSignaturesAlgorithmExtensionParser(Util.hexStringToByteArray("0003001DAD"));
        parser.parse();
    }

    @Test(expected = ParserException.class)
    public void testParseToLessLength() {
        SupportedSignaturesAlgorithmExtensionParser parser = new SupportedSignaturesAlgorithmExtensionParser(Util.hexStringToByteArray("0000001DAD"));
        parser.parse();
    }

    @Test(expected = ParserException.class)
    public void testParseWithGarbageDataLength() {
        SupportedSignaturesAlgorithmExtensionParser parser = new SupportedSignaturesAlgorithmExtensionParser(Util.hexStringToByteArray("00021DADFFFF"));
        parser.parse();
    }
}
