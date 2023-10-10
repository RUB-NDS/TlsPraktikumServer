package de.rub.nds.praktikum.protocol;

import de.rub.nds.praktikum.constants.ProtocolType;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * The handshake layer is responsible for the exchange of handshake messages
 * which are ultimately used to create the connection
 */
public class HandshakeLayer extends TlsSubProtocol {

    private final SessionContext context;

    private final RecordLayer recordLayer;

    /**
     * Constructor
     *
     * @param context     The SessionContext for which this handshake layer should
     *                    be constructed
     * @param recordLayer The record layer that should be used by this handshake
     *                    layer
     */
    public HandshakeLayer(SessionContext context, RecordLayer recordLayer) {
        super(ProtocolType.HANDSHAKE.getByteValue());
        this.context = context;
        this.recordLayer = recordLayer;
    }

    /**
     * Creates a ServerHelloMessage, serializes it sends via the RecordLayer and
     * updates the context accordingly. The message should contain a supported
     * versions extension and a keyshare extension.
     * <p>
     * *IMPORTANT* In this course you have to add these extensions in exactly
     * this order or you will not pass the unit tests!!! *IMPORTANT*
     */
    public void sendServerHello() {
        throw new UnsupportedOperationException("Add code here");
    }

    /**
     * Creates a HelloRetryRequest, serializes it sends via the RecordLayer and
     * updates the context accordingly. The message should contain a supported
     * versions extension and the keyshare extension.
     * <p>
     * *IMPORTANT* In this course you have to add these extensions in exactly
     * this order or you will not pass the unit tests!!! *IMPORTANT*
     */
    public void sendHelloRetryRequest() {
        throw new UnsupportedOperationException("Add code here");
    }

    /**
     * Creates a EncryptedExtensions message, serializes it sends via the
     * RecordLayer and updates the context accordingly.
     */
    public void sendEncryptedExtensions() {
        throw new UnsupportedOperationException("Add code here");
    }

    /**
     * Creates a Certificate message with the certificate chain from the
     * context, serializes it sends via the RecordLayer and updates the context
     * accordingly.
     */

    public void sendCertificates() {
        throw new UnsupportedOperationException("Add code here");
    }

    /**
     * Creates a CertificateVerify message, serializes it sends via the
     * RecordLayer and updates the context accordingly.
     */
    public void sendCertificateVerify() {
        throw new UnsupportedOperationException("Add code here");
    }

    /**
     * Creates a Finished message, serializes it sends via the RecordLayer and
     * updates the context accordingly.
     */
    public void sendFinished() {
        throw new UnsupportedOperationException("Add code here");
    }

    /**
     * Analyze byte stream and parse handshake messages. If a proper message is
     * received, update the TLS context. Consider creating several private
     * functions for processing different handshake messages.
     *
     * @param stream a TLS 1.3 handshake message from the client as a byte array
     */
    @Override
    public void processByteStream(byte[] stream) {
        throw new UnsupportedOperationException("Add code here");
    }


    /**
     * Example private function called from processByteStream. Parse handshakePayload, check if payload is
     * correct, handle ClientHello.
     *
     * @param stream           a TLS 1.3 handshake message from the client as a byte array
     * @param handshakePayload handshakePayload to be parsed
     */
    private void processClientHello(byte[] handshakePayload, byte[] stream) throws IOException {
        throw new UnsupportedOperationException("Add code here");
    }
}
