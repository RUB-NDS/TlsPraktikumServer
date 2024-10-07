package de.rub.nds.praktikum.messages;

import de.rub.nds.praktikum.constants.HandshakeMessageType;
import de.rub.nds.praktikum.messages.certificate.CertificateEntry;
import java.util.Collections;
import java.util.List;
import org.bouncycastle.crypto.tls.Certificate;

/**
 * This class represents a TLS certificate message. A certificate message
 * consists of a certificate request context and a list of certificate
 * entries. The certificate request is not necessary for this course and can be
 * 0x00 Each certificate entry consists of a certificate and an optional list of
 * certificate extensions. Certificate extensions are not necessary for this
 * course.
 */
public class CertificateMessage extends HandshakeMessage {

    private final List<CertificateEntry> certificateEntryList;

    /**
     * @param certificateChain The CertificateChain that should be transmitted
     * in this message
     */
    public CertificateMessage(Certificate certificateChain) {
        super(HandshakeMessageType.CERTIFICATE.getValue());
        throw new UnsupportedOperationException("Add code here");
    }

    public List<CertificateEntry> getCertificateEntryList() {
        return Collections.unmodifiableList(certificateEntryList);
    }
}
