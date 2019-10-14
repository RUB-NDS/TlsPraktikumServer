package de.rub.nds.praktikum.messages;

import de.rub.nds.praktikum.constants.CipherSuite;
import de.rub.nds.praktikum.constants.CompressionMethod;
import de.rub.nds.praktikum.constants.ProtocolVersion;
import de.rub.nds.praktikum.util.Util;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 *
 */
@Category(de.rub.nds.praktikum.Aufgabe2.class)
public class ClientHelloParserTest {

    private ClientHelloParser parser;

    @Test
    public void testParse() {
        ClientHelloParser parser = new ClientHelloParser(Util.hexStringToByteArray("03031ae8cc70c418da0ea1fbc86fcc165ce83a8105aa73af2cd3bad0c914f5b0db5520779b65b6b9796bd974acca5119f54ebd65a73ec9e372faf8fca04e051f271e15003e130213031301c02cc030009fcca9cca8ccaac02bc02f009ec024c028006bc023c0270067c00ac0140039c009c0130033009d009c003d003c0035002f00ff010000a70000000e000c0000096c6f63616c686f7374000b000403000102000a000c000a001d0017001e00190018002300000016000000170000000d0030002e040305030603080708080809080a080b080408050806040105010601030302030301020103020202040205020602002b0009080304030303020301002d00020101003300260024001d002014a89af64d5c41a76e9584445888700e3165cb7d7e09f4bdebc57f2fdb35682c"));
        ClientHello clientHello = parser.parse();
        assertEquals("Version is not equal", ProtocolVersion.TLS_1_2, clientHello.getVersion());
        assertArrayEquals("Random is not equal", Util.hexStringToByteArray("1ae8cc70c418da0ea1fbc86fcc165ce83a8105aa73af2cd3bad0c914f5b0db55"), clientHello.getRandom());
        assertArrayEquals("SessionId is not equal", Util.hexStringToByteArray("779b65b6b9796bd974acca5119f54ebd65a73ec9e372faf8fca04e051f271e15"), clientHello.getSessionId());
        assertEquals("Ciphersuites are not equal", 31, clientHello.getCiphersuiteList().size());
        assertTrue("Ciphersuites should contain TLS_AES_128_GCM_SHA256", clientHello.getCiphersuiteList().contains(CipherSuite.TLS_AES_128_GCM_SHA256));
        assertTrue("Ciphersuites should contain TLS_AES_256_GCM_SHA384", clientHello.getCiphersuiteList().contains(CipherSuite.TLS_AES_256_GCM_SHA384));
        assertTrue("Ciphersuites should contain TLS_CHACHA20_POLY1305_SHA256", clientHello.getCiphersuiteList().contains(CipherSuite.TLS_CHACHA20_POLY1305_SHA256));
        assertEquals("There should be exactly one compression method", 1, clientHello.getCompressionMethodList().size());
        assertTrue("The one compression method should be CompressionMethod.NULL", clientHello.getCompressionMethodList().contains(CompressionMethod.NULL));
        assertEquals("We parsed 4 extensions. The client hello actually contains 10 extension, but we only implement 4 this is fine for us", 4, clientHello.getExtensionList().size());
    }

}
