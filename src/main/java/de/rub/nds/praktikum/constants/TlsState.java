package de.rub.nds.praktikum.constants;

/**
 * Enum with possible states in the TLS state machine
 * 
 * the comment describes what was done to achieve the state
 */
public enum TlsState {

    /**
     * Starting the Handshake, waiting for a ClientHello message
     */
    START,
    /**
     * we already sent a finished message, waiting for client finished
     */
    WAIT_FINISHED,
    /**
     * we sent a HelloRetryRequest in response to the first ClientHello
     */
    RETRY_HELLO,
    /**
     * finished messages have been exchanged, app data can now be exchanged
     */
    CONNECTED,
    /**
     * a valid parameter choice is possible, we select parameters and send them
     */
    NEGOTIATED,
    /**
     * We received a ClientHello, we need to check if a valid parameter choice
     * is possible
     */
    RECVD_CH,
    /**
     * We sent a HelloRetryRequest and are waiting for a second ClientHello.
     */
    AWAIT_RETRY_HELLO_RESPONSE,
    /**
     * we entered an error state, we send an alert and close the connection
     */
    ERROR,
}
