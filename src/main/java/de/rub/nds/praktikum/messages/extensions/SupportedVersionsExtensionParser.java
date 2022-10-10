package de.rub.nds.praktikum.messages.extensions;

import de.rub.nds.praktikum.constants.ProtocolVersion;
import de.rub.nds.praktikum.exception.ParserException;
import de.rub.nds.praktikum.messages.Parser;
import java.util.LinkedList;
import java.util.List;

/**
 * A parser class which parses a provided byte[] into a supported versions
 * extension object
 */
public class SupportedVersionsExtensionParser extends Parser<SupportedVersionsExtension> {

    /**
     * Constructor
     *
     * @param array a byte[] to parse
     */
    public SupportedVersionsExtensionParser(byte[] array) {
        super(array);
    }

    @Override
    public SupportedVersionsExtension parse() {
        throw new UnsupportedOperationException("Add code here");
    }

}
