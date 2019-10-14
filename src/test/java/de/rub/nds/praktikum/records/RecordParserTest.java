package de.rub.nds.praktikum.records;

import de.rub.nds.praktikum.util.Util;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class RecordParserTest {

    public RecordParserTest() {
    }

    @Test
    @Category(de.rub.nds.praktikum.Aufgabe1.class)
    public void testParse() {
        RecordParser parser = new RecordParser(Util.hexStringToByteArray("050303000401020304"));
        Record parse = parser.parse();
        assertEquals(5, parse.getType());
        assertArrayEquals(new byte[]{0x03, 0x03}, parse.getVersion());
        assertArrayEquals(Util.hexStringToByteArray("01020304"), parse.getData());
    }
}
