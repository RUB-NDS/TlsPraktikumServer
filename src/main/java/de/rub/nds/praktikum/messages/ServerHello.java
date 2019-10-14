package de.rub.nds.praktikum.messages;

import de.rub.nds.praktikum.constants.CipherSuite;
import de.rub.nds.praktikum.constants.CompressionMethod;
import de.rub.nds.praktikum.constants.HandshakeMessageType;
import de.rub.nds.praktikum.constants.ProtocolVersion;
import de.rub.nds.praktikum.messages.extensions.Extension;
import java.util.Collections;
import java.util.List;

/**
 * This class represents a TLS server hello message. The server hello message
 * contains the algorithmic selection of the server as well as a list of
 * extension messages
 *
 */
public class ServerHello extends HandshakeMessage {

    private final ProtocolVersion version;
    private final byte[] random;
    private final byte[] sessionId;
    private final CipherSuite selectedCiphersuite;
    private final CompressionMethod selectedCompressionMethod;
    private final List<Extension> extensionList;

    /**
     * Constructor
     *
     * @param version the selected protocol version
     * @param random the random bytes (32) of the server
     * @param sessionId the session id (reflected from the client)
     * @param selectedCipherSuite the selected selectedCiphersuite
     * @param selectedCompressionMethod the selected compression method
     * @param extensionList the extension list of the server
     */
    public ServerHello(ProtocolVersion version, byte[] random, byte[] sessionId, CipherSuite selectedCipherSuite, CompressionMethod selectedCompressionMethod, List<Extension> extensionList) {
        super(HandshakeMessageType.SERVER_HELLO.getValue());
        this.version = version;
        this.random = random;
        this.sessionId = sessionId;
        this.selectedCiphersuite = selectedCipherSuite;
        this.selectedCompressionMethod = selectedCompressionMethod;
        this.extensionList = extensionList;
    }

    /**
     * Returns the selected protocol version
     *
     * @return the selected protocol version
     */
    public ProtocolVersion getVersion() {
        return version;
    }

    /**
     * Returns the random
     *
     * @return the random
     */
    public byte[] getRandom() {
        return random;
    }

    /**
     * Returns the bytes of the session id
     *
     * @return the bytes of the session id
     */
    public byte[] getSessionId() {
        return sessionId;
    }

    /**
     * Returns the selected selectedCiphersuite
     *
     * @return the selected selectedCiphersuite
     */
    public CipherSuite getSelectedCiphersuite() {
        return selectedCiphersuite;
    }

    /**
     * Returns the selected compression algorithm
     *
     * @return the selected compression algorithm
     */
    public CompressionMethod getSelectedCompressionMethod() {
        return selectedCompressionMethod;
    }

    /**
     * Returns the extension list of the server
     *
     * @return the extension list
     */
    public List<Extension> getExtensionList() {
        return Collections.unmodifiableList(extensionList);
    }

}
