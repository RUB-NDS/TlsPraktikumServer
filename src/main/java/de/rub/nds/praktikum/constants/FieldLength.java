package de.rub.nds.praktikum.constants;

/**
 * Enum with with the length of various fields in TLS
 */
public class FieldLength {

    /**
     * The length of the record length field
     */
    public static final int RECORD_LENGTH = 2;

    /**
     * The lenght of the handshake length field
     */
    public static final int HANDSHAKE_LENGTH = 3;

    /**
     *
     */
    public static final int VERSION = 2;

    /**
     *
     */
    public static final int RANDOM = 32;

    /**
     *
     */
    public static final int SESSION_ID_LENGTH = 1;

    /**
     *
     */
    public static final int CIPHER_SUITE_LEGNTH = 2;

    /**
     *
     */
    public static final int COMPRESION_LENGTH = 1;

    /**
     *
     */
    public static final int EXTENSIONS_LENGTH = 2;

    /**
     *
     */
    public static final int EXTENSION_TYPE = 2;

    /**
     *
     */
    public static final int EXTENSION_LENGTH = 2;

    /**
     *
     */
    public static final int KEYSHARE_LIST_LENGTH = 2;

    /**
     *
     */
    public static final int KEYSHARE_GROUP = 2;

    /**
     *
     */
    public static final int KEYSHARE_LENGTH = 2;

    /**
     *
     */
    public static final int NAMED_GROUPS_LENGTH = 2;

    /**
     *
     */
    public static final int SIGNATURE_ALGORITM_LENGTH = 2;
    /**
     * This is only in this course, the verify data length depends on the
     * negotiated hash function
     */
    public static final int VERIFY_DATA = 32;

    /**
     *
     */
    public static final int ALERT_DESCRIPTION = 1;

    /**
     *
     */
    public static final int ALERT_LEVEL = 1;

    /**
     *
     */
    public static final int CERTIFICATE_REQUEST_CONTEXT = 1;

    /**
     *
     */
    public static final int CERTIFICATE_LIST_LENGTH = 3;

    /**
     *
     */
    public static final int CERTIFICATE_ENTRY_LENGTH = 3;

    /**
     *
     */
    public static final int CERTIFICATE_EXTENSION_LENGTH = 2;

    /**
     *
     */
    public static final int CERTIFICATE_VERIFY_SIGNATURE_LENGTH = 2;

    private FieldLength() {
    }
}
