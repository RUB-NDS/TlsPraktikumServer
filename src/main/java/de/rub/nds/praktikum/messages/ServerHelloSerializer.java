package de.rub.nds.praktikum.messages;

import de.rub.nds.praktikum.exception.TlsException;
import de.rub.nds.praktikum.messages.extensions.Extension;
import de.rub.nds.praktikum.util.Util;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * A serializer class which transfroms a server hello message object into its
 * byte representation
 */
public class ServerHelloSerializer extends Serializer<ServerHello> {

    private final ServerHello hello;

    /**
     * Constructor
     *
     * @param hello The message to serialize
     */
    public ServerHelloSerializer(ServerHello hello) {
        this.hello = hello;
    }

    @Override
    protected void serializeBytes() {
        throw new UnsupportedOperationException("Add code here");
    }

}
