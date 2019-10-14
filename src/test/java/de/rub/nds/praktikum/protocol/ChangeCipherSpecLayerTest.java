package de.rub.nds.praktikum.protocol;

import de.rub.nds.praktikum.util.Util;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import org.bouncycastle.crypto.tls.Certificate;
import static org.junit.Assert.assertArrayEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(de.rub.nds.praktikum.Aufgabe2.class)
public class ChangeCipherSpecLayerTest {

    private ChangeCipherSpecLayer layer;

    private ByteArrayOutputStream stream;

    private SessionContext context;

    @Before
    public void setUp() {
        context = new SessionContext(Certificate.EMPTY_CHAIN, null);
        stream = new ByteArrayOutputStream();
        layer = new ChangeCipherSpecLayer(context, new RecordLayer(stream, new ByteArrayInputStream(new byte[0]), context, 0));
    }

    /**
     * Test of sendCCS method, of class ChangeCipherSpecLayer.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testSendCCS() throws Exception {
        byte[] digest = context.getDigest();
        layer.sendCCS();
        assertArrayEquals(Util.hexStringToByteArray("140303000101"), stream.toByteArray());
        assertArrayEquals("The digest is not affected by CCS messages", digest, context.getDigest());
    }
}
