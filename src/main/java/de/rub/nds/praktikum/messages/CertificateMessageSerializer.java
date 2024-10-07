package de.rub.nds.praktikum.messages;

/**
 * A serializer class which transforms a certificate message object into its
 * byte representation
 */
public class CertificateMessageSerializer extends Serializer<CertificateMessage> {

    private final CertificateMessage message;

    /**
     * Constructor
     *
     * @param message message that should be serialized
     */
    public CertificateMessageSerializer(CertificateMessage message) {
        this.message = message;
    }

    @Override
    protected void serializeBytes() {
        throw new UnsupportedOperationException("Add code here");
    }

}
