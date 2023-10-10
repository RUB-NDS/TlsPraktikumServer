package de.rub.nds.praktikum.messages.certificate;

import de.rub.nds.praktikum.messages.Serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * A serializer for a certificate entry
 */
public class CertificateEntrySerializer extends Serializer<CertificateEntry> {

    private final CertificateEntry certificateEntry;

    /**
     * Constructor
     *
     * @param certificateEntry The certificate Entry that should get serialized
     */
    public CertificateEntrySerializer(CertificateEntry certificateEntry) {
        this.certificateEntry = certificateEntry;
    }

    @Override
    protected void serializeBytes() {
        throw new UnsupportedOperationException("Add code here");
    }

}
