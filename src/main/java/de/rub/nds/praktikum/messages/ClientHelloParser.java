package de.rub.nds.praktikum.messages;

import de.rub.nds.praktikum.constants.CipherSuite;
import de.rub.nds.praktikum.constants.CompressionMethod;
import de.rub.nds.praktikum.constants.ExtensionType;
import de.rub.nds.praktikum.constants.FieldLength;
import de.rub.nds.praktikum.constants.ProtocolVersion;
import de.rub.nds.praktikum.exception.ParserException;
import de.rub.nds.praktikum.messages.extensions.Extension;
import de.rub.nds.praktikum.messages.extensions.KeyShareExtension;
import de.rub.nds.praktikum.messages.extensions.KeyShareExtensionParser;
import de.rub.nds.praktikum.messages.extensions.SupportedGroupsExtension;
import de.rub.nds.praktikum.messages.extensions.SupportedGroupsExtensionParser;
import de.rub.nds.praktikum.messages.extensions.SupportedSignaturesAlgorithmExtension;
import de.rub.nds.praktikum.messages.extensions.SupportedSignaturesAlgorithmExtensionParser;
import de.rub.nds.praktikum.messages.extensions.SupportedVersionsExtension;
import de.rub.nds.praktikum.messages.extensions.SupportedVersionsExtensionParser;
import de.rub.nds.praktikum.util.Util;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * A parser class which parses a provided byte[] into a client hello object
 *
 */
public class ClientHelloParser extends Parser<ClientHello> {

    private byte[] version;
    private byte[] random;
    private byte[] sessionId;
    private byte[] cipherSuites;
    private byte[] compressionMethods;
    private byte[] extensionBytes;

    /**
     * Constructor
     *
     * @param array byte[] that should be parsed
     */
    public ClientHelloParser(byte[] array) {
        super(array);
    }

    @Override
    public ClientHello parse() {
        throw new UnsupportedOperationException("Add code here");
    }

}
