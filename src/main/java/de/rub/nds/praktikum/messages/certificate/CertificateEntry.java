package de.rub.nds.praktikum.messages.certificate;

import de.rub.nds.praktikum.messages.extensions.Extension;
import java.util.List;
import org.bouncycastle.asn1.x509.Certificate;

/**
 * A single Entry in the CertificateMessage
 */
public class CertificateEntry {

    private final Certificate certificate;

    private final List<Extension> extensionList;

    /**
     *
     * @param certificate
     * @param extensionList
     */
    public CertificateEntry(Certificate certificate, List<Extension> extensionList) {
        this.certificate = certificate;
        this.extensionList = extensionList;
    }

    /**
     * Returns the certificate of this entry
     *
     * @return the certificate of this entry
     */
    public Certificate getCertificate() {
        return certificate;
    }

    /**
     * Returns a list of extensions for this entry. In this course this is
     * always empty.
     *
     * @return a list of extensions for this entry
     */
    public List<Extension> getExtensionList() {
        return extensionList;
    }
}
