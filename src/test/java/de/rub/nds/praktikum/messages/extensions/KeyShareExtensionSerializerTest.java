package de.rub.nds.praktikum.messages.extensions;

import de.rub.nds.praktikum.constants.NamedGroup;
import de.rub.nds.praktikum.util.Util;
import java.util.LinkedList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 *
 */
@Category(de.rub.nds.praktikum.Aufgabe2.class)
public class KeyShareExtensionSerializerTest {

    @Test
    public void testSerializeBytes() {
        KeyShareExtension extension = new KeyShareExtension(new KeyShareEntry(new byte[]{0, 1,}, new byte[]{1, 2, 3,}));
        KeyShareExtensionSerializer serializer = new KeyShareExtensionSerializer(extension);
        assertArrayEquals("KeyShare Extension with a single normal entry is wrong:" + Util.bytesToHexString(serializer.serialize()), Util.hexStringToByteArray("00010003010203"), serializer.serialize());
        List<KeyShareEntry> entryList = new LinkedList<>();
        entryList.add(new KeyShareEntry(new byte[]{0, 1,}, new byte[]{1, 2, 3,}));
        extension = new KeyShareExtension(entryList);
        serializer = new KeyShareExtensionSerializer(extension);
        assertArrayEquals("Same Keyshare with different Constructor failed", Util.hexStringToByteArray("00010003010203"), serializer.serialize());
    }

    @Test
    public void testEntrylessSerializeBytes() {
        KeyShareExtension extension = new KeyShareExtension(NamedGroup.ECDH_X25519);
        KeyShareExtensionSerializer serializer = new KeyShareExtensionSerializer(extension);
        assertArrayEquals("Serialized is not ECDH_X25519",Util.hexStringToByteArray("001d"), serializer.serialize());
    }
}
