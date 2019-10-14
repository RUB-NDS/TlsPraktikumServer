/**
 * TLS-Attacker - A Modular Penetration Testing Framework for TLS
 *
 * Copyright 2014-2017 Ruhr University Bochum / Hackmanit GmbH
 *
 * Licensed under Apache License 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package de.rub.nds.praktikum.constants;

/**
 * Enum with possible alert level values
 */
public enum AlertLevel {

    /**
     * The alert is non fatal, the connection could proceed
     */
    WARNING((byte) 1),
    /**
     * The alert is fatal, the connection has to be terminated
     */
    FATAL((byte) 2);

    private final byte value;

    private AlertLevel(byte value) {
        this.value = value;
    }

    /**
     * Returns the byte value of the AlertLevel
     *
     * @return byte value of the AlertLevel
     */
    public byte getValue() {
        return value;
    }

}
