package de.rub.nds.praktikum.messages;

import de.rub.nds.praktikum.constants.CipherSuite;
import de.rub.nds.praktikum.constants.CompressionMethod;
import de.rub.nds.praktikum.constants.NamedGroup;
import de.rub.nds.praktikum.constants.ProtocolVersion;
import de.rub.nds.praktikum.messages.extensions.KeyShareExtension;
import de.rub.nds.praktikum.messages.extensions.SupportedVersionsExtension;
import de.rub.nds.praktikum.util.Util;
import java.util.Arrays;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(de.rub.nds.praktikum.Aufgabe2.class)
public class HelloRetryRequestSerializerTest {

    @Test
    public void testSerializeBytes() {
        HelloRetryRequest hrr = new HelloRetryRequest(
                Util.hexStringToByteArray("9a1ef84d10280f5a45b9191b1e1ea7348f342a9feda5f47aad94f6a0fc4f2bc3"),
                CipherSuite.TLS_AES_256_GCM_SHA384,
                CompressionMethod.NULL,
                Arrays.asList(new SupportedVersionsExtension(ProtocolVersion.TLS_1_3),
                        new KeyShareExtension(NamedGroup.ECDH_X25519)));
        ServerHelloSerializer serializer = new ServerHelloSerializer(hrr);
        assertArrayEquals("Serialized HelloRetryRequest",
                Util.hexStringToByteArray("0303cf21ad74e59a6111be1d8c021e65b891c2a211167abb8c5e079e09e2c8a8339c209a1ef84d10280f5a45b9191b1e1ea7348f342a9feda5f47aad94f6a0fc4f2bc3130200000c002b0002030400330002001d"),
                serializer.serialize());
    }
}
