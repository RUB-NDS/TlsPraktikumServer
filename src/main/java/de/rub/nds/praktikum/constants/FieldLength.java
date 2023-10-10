package de.rub.nds.praktikum.constants;

/**
 * Enum with the length of various fields in TLS
 */
public class FieldLength {

    /**
     * The length of the record length field
     */
    public static final int RECORD_LENGTH = 2;

    /**
     * The length of the handshake length field
     */
    public static final int HANDSHAKE_LENGTH = 3;

    /**
     * The length of a version field
     */
    public static final int VERSION = 2;

    /**
     * The length of a version field
     */
    public static final int RANDOM = 32;

    /**
     * The length of the sessionId field
     */
    public static final int SESSION_ID_LENGTH = 1;

    /**
     * The length of the cipher suite length field
     */
    public static final int CIPHER_SUITE_LENGTH = 2;

    /**
     * The length of a compression field
     */
    public static final int COMPRESSION_LENGTH = 1;

    /**
     * The length of the extensions length field
     */
    public static final int EXTENSIONS_LENGTH = 2;

    /**
     * The length of an extension type field
     */
    public static final int EXTENSION_TYPE = 2;

    /**
     * The length of the extension length field
     */
    public static final int EXTENSION_LENGTH = 2;

    /**
     * The length of the keyshare list length field
     */
    public static final int KEYSHARE_LIST_LENGTH = 2;

    /**
     * The length of a keyshare group field
     */
    public static final int KEYSHARE_GROUP = 2;

    /**
     * The length of the keyshare field
     */
    public static final int KEYSHARE_LENGTH = 2;

    /**
     * The length of a named group field
     */
    public static final int NAMED_GROUPS_LENGTH = 2;

    /**
     * The length of a signature algorithm field
     */
    public static final int SIGNATURE_ALGORITHM_LENGTH = 2;
    /**
     * This is only in this course, the verify data length depends on the
     * negotiated hash function
     */
    public static final int VERIFY_DATA = 32;

    /**
     * The length of the alert description field
     */
    public static final int ALERT_DESCRIPTION = 1;

    /**
     * The length of the alert level field
     */
    public static final int ALERT_LEVEL = 1;

    /**
     *The length of the certificate request context field
     */
    public static final int CERTIFICATE_REQUEST_CONTEXT = 1;

    /**
     * The length of the certificate list length field
     */
    public static final int CERTIFICATE_LIST_LENGTH = 3;

    /**
     * The length of the certificate entry length field
     */
    public static final int CERTIFICATE_ENTRY_LENGTH = 3;

    /**
     * The length of the certificate extension length field
     */
    public static final int CERTIFICATE_EXTENSION_LENGTH = 2;

    /**
     * The length of the certificate verify signature length field
     */
    public static final int CERTIFICATE_VERIFY_SIGNATURE_LENGTH = 2;

    private FieldLength() {
    }
}
