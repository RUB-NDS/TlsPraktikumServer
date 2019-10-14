package de.rub.nds.praktikum.messages.extensions;

import de.rub.nds.praktikum.constants.ExtensionType;
import de.rub.nds.praktikum.constants.SignatureAndHashAlgorithm;
import de.rub.nds.praktikum.messages.Serializer;
import java.util.Collections;
import java.util.List;

/**
 * This class represents a supported signature and hash algorithmextension. The
 * signature and hash algorithm extension extension is used to inform the other
 * party about all supported signature and hash algorithms
 *
 */
public class SupportedSignaturesAlgorithmExtension extends Extension {

    private final List<SignatureAndHashAlgorithm> signatureAlgorithmsList;

    /**
     * Constructor
     *
     * @param signatureAlgorithmsList a list of supported signature and hash
     * algorithms
     */
    public SupportedSignaturesAlgorithmExtension(List<SignatureAndHashAlgorithm> signatureAlgorithmsList) {
        super(ExtensionType.SUPPORTED_SIGNATURES);
        this.signatureAlgorithmsList = signatureAlgorithmsList;
    }

    /**
     * Returns the list of signature and hash algorithms
     *
     * @return the list of signature and hash algorithms
     */
    public List<SignatureAndHashAlgorithm> getSignatureAlgorithmsList() {
        return Collections.unmodifiableList(signatureAlgorithmsList);
    }

    /**
     * Returns a supported signature and hash algorithm serializer
     *
     * @return a supported groups serializer
     */
    @Override
    public Serializer getSerializer() {
        throw new UnsupportedOperationException("The server does not need to support this ;)");
    }
}
