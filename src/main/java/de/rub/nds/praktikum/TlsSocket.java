package de.rub.nds.praktikum;

import de.rub.nds.praktikum.protocol.TlsProtocol;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * A Socket instantiation created from the TlsServerSocket
 *
 */
public class TlsSocket extends Socket {

    private final Socket realSocket;

    private final TlsOutputByteStream outputStream;
    private final TlsInputByteStream inputStream;
    private final TlsProtocol tlsProtocol;

    /**
     * Constructor
     *
     * @param socket Raw underlying socket
     * @param protocol The TlsProtocol which performed the handshake
     * @throws UnknownHostException If the host is unknown
     * @throws IOException If something goes wrong with the socket
     */
    public TlsSocket(Socket socket, TlsProtocol protocol) throws UnknownHostException, IOException {
        super();
        this.tlsProtocol = protocol;
        this.realSocket = socket;
        outputStream = new TlsOutputByteStream(protocol);
        inputStream = new TlsInputByteStream(protocol);
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        return outputStream;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return inputStream;
    }

    @Override
    public boolean isClosed() {
        return realSocket.isClosed(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized void close() throws IOException {
        tlsProtocol.beforeClose();
        realSocket.close();
    }

}
