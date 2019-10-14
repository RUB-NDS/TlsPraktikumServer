package de.rub.nds.praktikum.messages;

import de.rub.nds.praktikum.constants.CipherSuite;
import de.rub.nds.praktikum.constants.CompressionMethod;
import de.rub.nds.praktikum.constants.HandshakeMessageType;
import de.rub.nds.praktikum.constants.ProtocolVersion;
import de.rub.nds.praktikum.messages.extensions.Extension;
import java.util.Collections;
import java.util.List;

/**
 * This class represents a TLS client hello message. The client hello message
 * contains all the cryptographic parameters proposed by the client as well as a
 * list of extensions.
 *
 */
public class ClientHello extends HandshakeMessage {

    private final ProtocolVersion version;
    private final byte[] random;
    private final byte[] sessionId;
    private final List<CipherSuite> ciphersuiteList;
    private final List<CompressionMethod> compressionMethodList;
    private final List<Extension> extensionList;

    /**
     * Constructor
     *
     * @param version the highest supported protocol version of the client
     * @param random 32 random bytes used as a nonce
     * @param sessionId a session id which can be used to resume sessions
     * @param ciphersuiteList a list of the client supported ciphersuites
     * @param compressionMethodList a list of the client supported compression
     * methods
     * @param extensionList a list of client supported extensions
     */
    public ClientHello(ProtocolVersion version, byte[] random, byte[] sessionId, List<CipherSuite> ciphersuiteList, List<CompressionMethod> compressionMethodList, List<Extension> extensionList) {
        super(HandshakeMessageType.CLIENT_HELLO.getValue());
        this.version = version;
        this.random = random;
        this.sessionId = sessionId;
        this.ciphersuiteList = ciphersuiteList;
        this.compressionMethodList = compressionMethodList;
        this.extensionList = extensionList;
    }

    /**
     * Returns the highest supported version by the client. Note that this does
     * not respect protocol version extension messages
     *
     * @return the highest supported version by the client as a ProtocolVersion
     */
    public ProtocolVersion getVersion() {
        return version;
    }

    /**
     * Returns the client random
     *
     * @return the client random
     */
    public byte[] getRandom() {
        return random;
    }

    /**
     * Returns the sessionId
     *
     * @return the sessionId
     */
    public byte[] getSessionId() {
        return sessionId;
    }

    /**
     * Returns the ciphersuiteList
     *
     * @return the ciphersuiteList
     */
    public List<CipherSuite> getCiphersuiteList() {
        return Collections.unmodifiableList(ciphersuiteList);
    }

    /**
     * Returns the compression methods
     *
     * @return the compression methods
     */
    public List<CompressionMethod> getCompressionMethodList() {
        return Collections.unmodifiableList(compressionMethodList);
    }

    /**
     * Returns the extension list
     *
     * @return the extension list
     */
    public List<Extension> getExtensionList() {
        return Collections.unmodifiableList(extensionList);
    }
}
