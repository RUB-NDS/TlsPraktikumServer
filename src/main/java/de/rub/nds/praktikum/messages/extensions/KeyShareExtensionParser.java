package de.rub.nds.praktikum.messages.extensions;

import de.rub.nds.praktikum.constants.FieldLength;
import de.rub.nds.praktikum.exception.ParserException;
import de.rub.nds.praktikum.messages.Parser;
import de.rub.nds.praktikum.util.Util;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * A parser class which parses a provided byte[] into a key share extension
 * object
 *
 */
public class KeyShareExtensionParser extends Parser<KeyShareExtension> {

    /**
     * Constructor
     *
     * @param array the byte[] that should be parsed
     */
    public KeyShareExtensionParser(byte[] array) {
        super(array);
    }

    @Override
    public KeyShareExtension parse() {
        throw new UnsupportedOperationException("Add code here");
    }
}
