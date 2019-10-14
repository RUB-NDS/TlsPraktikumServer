package de.rub.nds.praktikum.messages.certificate;

import de.rub.nds.praktikum.util.Util;
import java.io.IOException;
import java.util.LinkedList;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Category(de.rub.nds.praktikum.Aufgabe4.class)
public class CertificateEntrySerializerTest {

    /**
     * Test of serializeBytes method, of class CertificateEntrySerializer.
     *
     * @throws java.io.IOException
     */
    @Test
    public void testSerializeBytes() throws IOException {
        org.bouncycastle.asn1.x509.Certificate cert1 = mock(org.bouncycastle.asn1.x509.Certificate.class);
        when(cert1.getEncoded()).thenReturn(new byte[]{1, 2, 3});
        //A Certificate chain consisting of only 1 certificate with the bytes 1 2 3 
        CertificateEntry certEntry = new CertificateEntry(cert1, new LinkedList<>());
        CertificateEntrySerializer serializer = new CertificateEntrySerializer(certEntry);
        assertArrayEquals("A single CerticiateEntry should contain a length field, the cert bytes, the extension length bytes and (not considered in this course) extension bytes", Util.hexStringToByteArray("0000030102030000"), serializer.serialize());
    }
}
