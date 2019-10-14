package de.rub.nds.praktikum.messages.extensions;

import de.rub.nds.praktikum.messages.Parser;

/**
 * A parser for the supported groups extension
 */
public class SupportedGroupsExtensionParser extends Parser<SupportedGroupsExtension> {

    /**
     * Constructor
     *
     * @param array the byte[] that should be parsed
     */
    public SupportedGroupsExtensionParser(byte[] array) {
        super(array);
    }

    @Override
    public SupportedGroupsExtension parse() {
        throw new UnsupportedOperationException("Add code here");
    }

}
