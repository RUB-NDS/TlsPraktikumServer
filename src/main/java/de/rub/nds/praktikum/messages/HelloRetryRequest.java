package de.rub.nds.praktikum.messages;

import de.rub.nds.praktikum.constants.CipherSuite;
import de.rub.nds.praktikum.constants.CompressionMethod;
import de.rub.nds.praktikum.constants.ProtocolVersion;
import de.rub.nds.praktikum.messages.extensions.Extension;
import static de.rub.nds.praktikum.util.Util.hexStringToByteArray;
import java.util.List;

/**
 * A HelloRetryRequest is dispatched by the server in case it was able to find
 * an acceptable set of parameters but the ClientHello does not contain
 * sufficient information to proceed with the handshake (i.e. wrong or missing
 * key share) [see RFC8446, p. 33 ff].
 *
 */
public class HelloRetryRequest extends ServerHello {

    /**
     *
     * @return
     */
    public static byte[] randomValue() {
        return hexStringToByteArray("cf21ad74e59a6111be1d8c021e65b891c2a211167abb8c5e079e09e2c8a8339c");
    }

    /**
     *
     * @param sessionId
     * @param ciphersuite
     * @param compression
     * @param extensionList
     */
    public HelloRetryRequest(byte[] sessionId, CipherSuite ciphersuite, CompressionMethod compression, List<Extension> extensionList) {
        super(ProtocolVersion.TLS_1_2,
                HelloRetryRequest.randomValue(),
                sessionId,
                ciphersuite,
                compression,
                extensionList);

    }
}
