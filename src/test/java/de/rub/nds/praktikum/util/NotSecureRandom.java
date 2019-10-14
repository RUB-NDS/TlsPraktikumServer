package de.rub.nds.praktikum.util;

import java.security.SecureRandom;

/**
 * A random number generator which always returns the same rigged byte...
 */
public class NotSecureRandom extends SecureRandom {

    private final byte b;

    /**
     *
     * @param b
     */
    public NotSecureRandom(byte b) {
        this.b = b;
    }

    /**
     *
     * @param bytes
     */
    @Override
    public void nextBytes(byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = b;
        }
    }

}
