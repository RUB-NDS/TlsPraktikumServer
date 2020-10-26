package de.rub.nds.praktikum.messages;

import de.rub.nds.praktikum.util.Util;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 *
 */
@Category(de.rub.nds.praktikum.Aufgabe1.class)
public class AlertSerializerTest {

    @Test
    public void testSerializeBytes() {
        Alert alert = new Alert((byte) 2, (byte) 20);
        AlertSerializer serializer = new AlertSerializer(alert);
        byte[] serializedAlert = serializer.serialize();
        Assert.assertArrayEquals("Alert is not fatal BAD_RECORD_MAC",Util.hexStringToByteArray("0214"), serializedAlert);
    }

}
