package de.rub.nds.praktikum.messages.extensions;

import de.rub.nds.praktikum.constants.NamedGroup;
import de.rub.nds.praktikum.exception.ParserException;
import de.rub.nds.praktikum.util.Util;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 *
 */
@Category(de.rub.nds.praktikum.Aufgabe2.class)
public class SupportedGroupsExtensionParserTest {

    @Test
    public void testParseValid() {
        SupportedGroupsExtensionParser parser = new SupportedGroupsExtensionParser(Util.hexStringToByteArray("0002001D"));
        SupportedGroupsExtension extension = parser.parse();
        assertEquals("There is exactly one entry in the list", 1, extension.getNamedGroupList().size());
        assertEquals("The entry in the list is X25519", NamedGroup.ECDH_X25519, extension.getNamedGroupList().get(0));
        parser = new SupportedGroupsExtensionParser(Util.hexStringToByteArray("0004001D001D"));
        extension = parser.parse();
        assertEquals("There are exactly two entries in the list", 2, extension.getNamedGroupList().size());
        assertEquals("The first entry is X25519", NamedGroup.ECDH_X25519, extension.getNamedGroupList().get(0));
        assertEquals("The second entry is X25519 aswell", NamedGroup.ECDH_X25519, extension.getNamedGroupList().get(1));
    }

    @Test(expected = ParserException.class)
    public void testParseInvalidLength() {
        SupportedGroupsExtensionParser parser = new SupportedGroupsExtensionParser(Util.hexStringToByteArray("0004001D"));
        parser.parse();
    }

    @Test(expected = ParserException.class)
    public void testParseUnevenLength() {
        SupportedGroupsExtensionParser parser = new SupportedGroupsExtensionParser(Util.hexStringToByteArray("0003001DAD"));
        parser.parse();
    }

    @Test(expected = ParserException.class)
    public void testParseToLessLength() {
        SupportedGroupsExtensionParser parser = new SupportedGroupsExtensionParser(Util.hexStringToByteArray("0000001DAD"));
        parser.parse();
    }

    @Test(expected = ParserException.class)
    public void testParseWithGarbageDataLength() {
        SupportedGroupsExtensionParser parser = new SupportedGroupsExtensionParser(Util.hexStringToByteArray("00021DADFFFF"));
        parser.parse();
    }
}
