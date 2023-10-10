/**
 * TLS-Attacker - A Modular Penetration Testing Framework for TLS
 *
 * Copyright 2014-2017 Ruhr University Bochum / Hackmanit GmbH
 *
 * Licensed under Apache License 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package de.rub.nds.praktikum.constants;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * Enum with possible compression method values
 */
public enum CompressionMethod {

    /**
     * No Compression is used
     */
    NULL(new byte[]{0x00});

    private final byte[] value;

    private CompressionMethod(byte[] value) {
        this.value = value;
    }

    /**
     * Returns the byte[] value of the CompressionMethod
     *
     * @return byte value[] of the CompressionMethod
     */
    public byte[] getValue() {
        return value;
    }

    /**
     * Converts a byte[] into a compressionMethod. If the byte[] is not
     * recognized null is returned
     *
     * @param value the value to convert
     * @return the according compressionMethod. Null if the compressionMethod is
     * not recognized
     */
    public static CompressionMethod convert(byte[] value) {
        if (value.length != 1) {
            throw new IllegalArgumentException("CompressionMethod value is not 1 byte long");
        }
        for (CompressionMethod compressionMethod : CompressionMethod.values()) {
            if (Arrays.equals(compressionMethod.getValue(), value)) {
                return compressionMethod;
            }
        }
        return null;
    }

    /**
     * Converts a byte[] into a list of compressionMethods. The byte[] has to be
     * a multiple of 2 bytes long. If the byte array is empty an empty List is
     * returned
     *
     * @param values byte[] which should be converted
     * @return A List with the converted CompressionMethods
     */
    public static List<CompressionMethod> convertToList(byte[] values) {
        List<CompressionMethod> compressionMethod = new LinkedList<>();
        int pointer = 0;
        while (pointer < values.length) {
            byte[] method = new byte[1];
            method[0] = values[pointer];
            compressionMethod.add(convert(method));
            pointer += 1;
        }
        return compressionMethod;
    }

}
