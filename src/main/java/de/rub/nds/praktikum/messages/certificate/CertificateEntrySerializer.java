package de.rub.nds.praktikum.messages.certificate;

import de.rub.nds.praktikum.messages.Serializer;

/**
 * A serializer for a certificate entry
 */
public class CertificateEntrySerializer extends Serializer<CertificateEntry> {

    private final CertificateEntry certificateEntry;

    /**
     * Contructor
     *
     * @param certificateEntry The certificate Entry that should get serialized
     */
    public CertificateEntrySerializer(CertificateEntry certificateEntry) {
        this.certificateEntry = certificateEntry;
    }

    @Override
    protected byte[] serializeBytes() {
        throw new UnsupportedOperationException("Add code here");
    }

}
