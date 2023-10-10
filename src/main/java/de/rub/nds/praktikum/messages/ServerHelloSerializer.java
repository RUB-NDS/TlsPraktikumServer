package de.rub.nds.praktikum.messages;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * A serializer class which transforms a server hello message object into its
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
