package de.rub.nds.praktikum.messages;

import de.rub.nds.praktikum.constants.FieldLength;
import de.rub.nds.praktikum.exception.TlsException;
import de.rub.nds.praktikum.messages.certificate.CertificateEntry;
import de.rub.nds.praktikum.messages.certificate.CertificateEntrySerializer;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * A serializer class which transforms a certificate message object into its
 * byte representation
 *
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
