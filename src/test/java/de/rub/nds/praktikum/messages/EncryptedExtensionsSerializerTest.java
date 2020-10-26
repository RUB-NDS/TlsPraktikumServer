package de.rub.nds.praktikum.messages;

import de.rub.nds.praktikum.util.Util;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 *
 */
@Category(de.rub.nds.praktikum.Aufgabe4.class)
public class EncryptedExtensionsSerializerTest {

    @Test
    public void testSerializeBytes() {
        EncryptedExtensions message = new EncryptedExtensions();
        EncryptedExtensionsSerializer serializer = new EncryptedExtensionsSerializer(message);
        assertArrayEquals("Empty EncryptedExtensions contains only two zero bytes",Util.hexStringToByteArray("0000"), serializer.serialize());
    }

}
