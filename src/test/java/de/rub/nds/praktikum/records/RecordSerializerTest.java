package de.rub.nds.praktikum.records;

import de.rub.nds.praktikum.util.Util;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 *
 */
public class RecordSerializerTest {

    public RecordSerializerTest() {
    }

    @Test
    @Category(de.rub.nds.praktikum.Aufgabe1.class)
    public void testSerializeBytes() {
        Record record = new Record((byte) 3, new byte[]{0x03, 0x03}, new byte[]{01, 02, 03, 04});
        RecordSerializer serializer = new RecordSerializer(record);
        byte[] serializeBytes = serializer.serialize();
        assertArrayEquals("Record serialize failed",serializeBytes, Util.hexStringToByteArray("030303000401020304"));
    }

}
