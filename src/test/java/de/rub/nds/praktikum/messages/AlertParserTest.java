package de.rub.nds.praktikum.messages;

import de.rub.nds.praktikum.constants.AlertDescription;
import de.rub.nds.praktikum.constants.AlertLevel;
import de.rub.nds.praktikum.exception.ParserException;
import de.rub.nds.praktikum.util.Util;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 *
 */
@Category(de.rub.nds.praktikum.Aufgabe1.class)
public class AlertParserTest {

    @Test
    public void testParse() {
        AlertParser parser = new AlertParser(Util.hexStringToByteArray("0214"));
        Alert parsedAlert = parser.parse();
        assertEquals("The level of this alert is warning", AlertLevel.FATAL.getValue(), parsedAlert.getLevel());
        assertEquals("The description of this alert is BAD_RECORD_MAC", AlertDescription.BAD_RECORD_MAC.getValue(), parsedAlert.getDescription());
        parser = new AlertParser(Util.hexStringToByteArray("0130"));
        parsedAlert = parser.parse();
        assertEquals("The level of this alert is warning", AlertLevel.WARNING.getValue(), parsedAlert.getLevel());
        assertEquals("The description of this alert is UNKNOWN_CA", AlertDescription.UNKNOWN_CA.getValue(), parsedAlert.getDescription());

    }

    @Test(expected = ParserException.class)
    public void testParseWithGarbageDataLength() {
        AlertParser parser = new AlertParser(Util.hexStringToByteArray("021400"));
        parser.parse();
    }

}
