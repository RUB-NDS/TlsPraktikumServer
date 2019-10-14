package de.rub.nds.praktikum.constants;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Enum with possible signature and hash algorithm values
 */
public enum SignatureAndHashAlgorithm {
    ANONYMOUS_NONE(new byte[]{0x00, 0x00}),
    ANONYMOUS_MD5(new byte[]{0x01, 0x00}),
    ANONYMOUS_SHA1(new byte[]{0x02, 0x00}),
    ANONYMOUS_SHA224(new byte[]{0x03, 0x00}),
    ANONYMOUS_SHA256(new byte[]{0x04, 0x00}),
    ANONYMOUS_SHA384(new byte[]{0x05, 0x00}),
    ANONYMOUS_SHA512(new byte[]{0x06, 0x00}),
    RSA_NONE(new byte[]{0x00, 0x01}),
    RSA_MD5(new byte[]{0x01, 0x01}),
    RSA_SHA1(new byte[]{0x02, 0x01}),
    RSA_SHA224(new byte[]{0x03, 0x01}),
    RSA_SHA256(new byte[]{0x04, 0x01}),
    RSA_SHA384(new byte[]{0x05, 0x01}),
    RSA_SHA512(new byte[]{0x06, 0x01}),
    DSA_NONE(new byte[]{0x00, 0x02}),
    DSA_MD5(new byte[]{0x01, 0x02}),
    DSA_SHA1(new byte[]{0x02, 0x02}),
    DSA_SHA224(new byte[]{0x03, 0x02}),
    DSA_SHA256(new byte[]{0x04, 0x02}),
    DSA_SHA384(new byte[]{0x05, 0x02}),
    DSA_SHA512(new byte[]{0x06, 0x02}),
    ECDSA_NONE(new byte[]{0x00, 0x03}),
    ECDSA_MD5(new byte[]{0x01, 0x03}),
    ECDSA_SHA1(new byte[]{0x02, 0x03}),
    ECDSA_SHA224(new byte[]{0x03, 0x03}),
    ECDSA_SHA256(new byte[]{0x04, 0x03}),
    ECDSA_SHA384(new byte[]{0x05, 0x03}),
    ECDSA_SHA512(new byte[]{0x06, 0x03}),
    ED25519(new byte[]{0x08, 0x07}),
    ED448(new byte[]{0x08, 0x08}),
    /* RSASSA-PSS algorithms with public key OID rsaEncryption */
    RSA_PSS_RSAE_SHA256(new byte[]{0x08, 0x04}),
    RSA_PSS_RSAE_SHA384(new byte[]{0x08, 0x05}),
    RSA_PSS_RSAE_SHA512(new byte[]{0x08, 0x06}),
    /* RSASSA-PSS algorithms with public key OID RSASSA-PSS */
    RSA_PSS_PSS_SHA256(new byte[]{0x08, 0x09}),
    RSA_PSS_PSS_SHA384(new byte[]{0x08, 0x0a}),
    RSA_PSS_PSS_SHA512(new byte[]{0x08, 0x0b}),
    GOSTR34102001_GOSTR3411(new byte[]{(byte) 0xED, (byte) 0xED}),
    GOSTR34102012_256_GOSTR34112012_256(new byte[]{(byte) 0xEE, (byte) 0xEE}),
    GOSTR34102012_512_GOSTR34112012_512(new byte[]{(byte) 0xEF, (byte) 0xEF});

    private final byte[] value;

    private SignatureAndHashAlgorithm(byte[] value) {
        this.value = value;
    }

    /**
     * Returns the byte[] value of the SignatureAndHashAlgorithm
     *
     * @return the byte[] value of the SignatureAndHashAlgorithm
     */
    public byte[] getValue() {
        return value;
    }

    /**
     * Converts a byte[] into a SignatureAndHashAlgorithm. If the byte[] is not
     * recognized null is returned
     *
     * @param value the value to convert
     * @return the according SignatureAndHashAlgorithm. Null if the
     * SignatureAndHashAlgorithm is not recognized
     */
    public static SignatureAndHashAlgorithm convert(byte[] value) {
        if (value.length != 2) {
            throw new IllegalArgumentException("SignatureAndHashAlgorithm value is not 2 bytes long");
        }
        for (SignatureAndHashAlgorithm signatureAndHashAlgorithm : SignatureAndHashAlgorithm.values()) {
            if (Arrays.equals(signatureAndHashAlgorithm.getValue(), value)) {
                return signatureAndHashAlgorithm;
            }
        }
        return null;
    }

    /**
     * Converts a byte[] into a list of SignatureAndHashAlgorithms. The byte[]
     * has to be a multiple of 2 bytes long. If the byte array is empty an empty
     * List is returned
     *
     * @param values byte[] which should be converted
     * @return A List with the converted SignatureAndHashAlgorithms
     */
    public static List<SignatureAndHashAlgorithm> convertToList(byte[] values) {
        List<SignatureAndHashAlgorithm> signatureAndHashAlgorithms = new LinkedList<>();
        int pointer = 0;
        if (values.length % 2 != 0) {
            throw new IllegalArgumentException("SignatureAndHashAlgorithm is uneven length!");
        }
        while (pointer < values.length) {
            byte[] algo = new byte[2];
            algo[0] = values[pointer];
            algo[1] = values[pointer + 1];
            SignatureAndHashAlgorithm tempAlgo = convert(algo);
            signatureAndHashAlgorithms.add(tempAlgo);
            pointer += 2;
        }
        return signatureAndHashAlgorithms;
    }

}
