package de.rub.nds.praktikum.messages;

import de.rub.nds.praktikum.constants.FieldLength;

/**
 * A serializer class which transforms a certificate verify message object into
 * its byte representation
 *
 */
public class CertificateVerifySerializer extends Serializer<CertificateVerify> {

    private final CertificateVerify certVerify;

    /**
     * Constructor
     *
     * @param certVerify The certificate verify message to serialize
     */
    public CertificateVerifySerializer(CertificateVerify certVerify) {
        this.certVerify = certVerify;
    }

    @Override
    protected void serializeBytes() {
        throw new UnsupportedOperationException("Add code here");
    }

}
