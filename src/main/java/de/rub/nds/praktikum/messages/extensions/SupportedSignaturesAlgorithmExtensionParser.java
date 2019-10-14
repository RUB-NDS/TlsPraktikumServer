package de.rub.nds.praktikum.messages.extensions;

import de.rub.nds.praktikum.messages.Parser;

/**
 * A parser for the supported signatures and hash algorithms extension
 */
public class SupportedSignaturesAlgorithmExtensionParser extends Parser<SupportedSignaturesAlgorithmExtension> {

    /**
     * Constructor
     *
     * @param array The byte[] that should be parsed
     */
    public SupportedSignaturesAlgorithmExtensionParser(byte[] array) {
        super(array);
    }

    @Override
    public SupportedSignaturesAlgorithmExtension parse() {
            throw new UnsupportedOperationException("Add code here");
    }

}
