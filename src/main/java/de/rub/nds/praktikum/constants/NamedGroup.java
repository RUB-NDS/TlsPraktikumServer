package de.rub.nds.praktikum.constants;

import java.util.Arrays;

/**
 * Enum with possible named group values
 */
public enum NamedGroup {

    /**
     * X25519=0x0029 This group has to be implemented, the others are not
     * necessary
     */
    ECDH_X25519(new byte[]{(byte) 0, (byte) 29}),
    /*
     * These groups are not necessary to implement 
     */
    SECT163K1(new byte[]{(byte) 0, (byte) 1}),
    SECT163R1(new byte[]{(byte) 0, (byte) 2}),
    SECT163R2(new byte[]{(byte) 0, (byte) 3}),
    SECT193R1(new byte[]{(byte) 0, (byte) 4}),
    SECT193R2(new byte[]{(byte) 0, (byte) 5}),
    SECT233K1(new byte[]{(byte) 0, (byte) 6}),
    SECT233R1(new byte[]{(byte) 0, (byte) 7}),
    SECT239K1(new byte[]{(byte) 0, (byte) 8}),
    SECT283K1(new byte[]{(byte) 0, (byte) 9}),
    SECT283R1(new byte[]{(byte) 0, (byte) 10}),
    SECT409K1(new byte[]{(byte) 0, (byte) 11}),
    SECT409R1(new byte[]{(byte) 0, (byte) 12}),
    SECT571K1(new byte[]{(byte) 0, (byte) 13}),
    SECT571R1(new byte[]{(byte) 0, (byte) 14}),
    SECP160K1(new byte[]{(byte) 0, (byte) 15}),
    SECP160R1(new byte[]{(byte) 0, (byte) 16}),
    SECP160R2(new byte[]{(byte) 0, (byte) 17}),
    SECP192K1(new byte[]{(byte) 0, (byte) 18}),
    SECP192R1(new byte[]{(byte) 0, (byte) 19}),
    SECP224K1(new byte[]{(byte) 0, (byte) 20}),
    SECP224R1(new byte[]{(byte) 0, (byte) 21}),
    SECP256K1(new byte[]{(byte) 0, (byte) 22}),
    SECP256R1(new byte[]{(byte) 0, (byte) 23}),
    SECP384R1(new byte[]{(byte) 0, (byte) 24}),
    SECP521R1(new byte[]{(byte) 0, (byte) 25}),
    BRAINPOOLP256R1(new byte[]{(byte) 0, (byte) 26}),
    BRAINPOOLP384R1(new byte[]{(byte) 0, (byte) 27}),
    BRAINPOOLP512R1(new byte[]{(byte) 0, (byte) 28}),
    ECDH_X448(new byte[]{(byte) 0, (byte) 30}),
    FFDHE2048(new byte[]{(byte) 1, (byte) 0}),
    FFDHE3072(new byte[]{(byte) 1, (byte) 1}),
    FFDHE4096(new byte[]{(byte) 1, (byte) 2}),
    FFDHE6144(new byte[]{(byte) 1, (byte) 3}),
    FFDHE8192(new byte[]{(byte) 1, (byte) 4});

    private final byte[] value;

    private NamedGroup(byte[] value) {
        this.value = value;
    }

    /**
     * Returns the byte[] value of this group
     *
     * @return the byte[] value of this group
     */
    public byte[] getValue() {
        return value;
    }

    /**
     * Converts a byte[] to a NamedGroup. Returns null if the group is not
     * recognized
     *
     * @param value the byte[] to convert
     * @return Null if the value is not recognized
     */
    public static NamedGroup convert(byte[] value) {
        if (value.length != 2) {
            throw new IllegalArgumentException("NamedGroup value is not 2 bytes long");
        }
        for (NamedGroup namedGroup : NamedGroup.values()) {
            if (Arrays.equals(namedGroup.getValue(), value)) {
                return namedGroup;
            }
        }
        return null;
    }
}
