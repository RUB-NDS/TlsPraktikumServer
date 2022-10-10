package de.rub.nds.praktikum.messages;

import de.rub.nds.praktikum.exception.ParserException;

/**
 * A parser class for the finished message which transforms a byte[] into a
 * finished message
 */
public class FinishedParser extends Parser<Finished> {

    /**
     * Constructor
     *
     * @param array the array to parse
     */
    public FinishedParser(byte[] array) {
        super(array);
    }

    @Override
    public Finished parse() {
        throw new UnsupportedOperationException("Add code here");
    }
}
