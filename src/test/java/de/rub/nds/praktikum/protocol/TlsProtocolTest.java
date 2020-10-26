package de.rub.nds.praktikum.protocol;

import de.rub.nds.praktikum.constants.CipherSuite;
import de.rub.nds.praktikum.constants.CompressionMethod;
import de.rub.nds.praktikum.constants.NamedGroup;
import de.rub.nds.praktikum.constants.ProtocolVersion;
import de.rub.nds.praktikum.constants.SignatureAndHashAlgorithm;
import de.rub.nds.praktikum.constants.TlsState;
import de.rub.nds.praktikum.crypto.KeyGenerator;
import de.rub.nds.praktikum.exception.TlsException;
import de.rub.nds.praktikum.messages.extensions.KeyShareEntry;
import de.rub.nds.praktikum.util.NotSecureRandom;
import de.rub.nds.praktikum.util.Util;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.spec.ECGenParameterSpec;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.bouncycastle.crypto.tls.Certificate;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.test.TestRandomData;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 *
 */
public class TlsProtocolTest {

    private static KeyPair pair;

    @BeforeClass
    public static void setUpClass() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException {
        Security.addProvider(new BouncyCastleProvider());
        ECGenParameterSpec ecGenSpec = new ECGenParameterSpec("secp256r1");
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("ECDSA", "BC");
        keyPairGenerator.initialize(ecGenSpec, new TestRandomData(Util.hexStringToByteArray("c1a0d20c70a2fdfbd45553db21ce66c3aa1a53c082c92e1bfb178360b8723ed4")));
        pair = keyPairGenerator.generateKeyPair();

    }
    private TlsProtocol protocol;
    private Socket socket;
    private ByteArrayOutputStream outputStream;
    private ByteArrayInputStream inputStream;

    @Before
    public void setUp() throws IOException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        socket = mock(Socket.class);
        outputStream = new ByteArrayOutputStream();
        inputStream = new ByteArrayInputStream(new byte[]{});
        when(socket.getInputStream()).thenReturn(inputStream);
        when(socket.getOutputStream()).thenReturn(outputStream);
        protocol = new TlsProtocol(socket, Certificate.EMPTY_CHAIN, pair.getPrivate(), 10);
        protocol = spy(protocol);
        SessionContext context = spy(new SessionContext(Certificate.EMPTY_CHAIN, pair.getPrivate()));
        when(context.getSecureRandom()).thenReturn(new NotSecureRandom((byte) 0x02));
        when(protocol.getContext()).thenReturn(context);

    }

    @Test(expected = TlsException.class) //Test that connection times out if we do not receive a client hello
    @Category(de.rub.nds.praktikum.Aufgabe2.class)
    public void testInitSessionNoClientHello() throws Exception {
        protocol.getContext().setTlsState(TlsState.START);
        protocol.stepConnectionState();
    }

    @Test
    @Category(de.rub.nds.praktikum.Aufgabe2.class)
    public void testInitSessionClientHelloRetry() throws Exception {
        socket = mock(Socket.class);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Util.hexStringToByteArray("16030301320100012e0303d2070dda5da15b5b1e8df24392f06794436f684f4cde088fd852d7c0b6fdff4c20781bf656122613ab8dfdea009961ebe4bcacc71f1f5547c8a2f753273f2f68ad003e130213031301c02cc030009fcca9cca8ccaac02bc02f009ec024c028006bc023c0270067c00ac0140039c009c0130033009d009c003d003c0035002f00ff010000a70000000e000c0000096c6f63616c686f7374000b000403000102000a000c000a001d0017001e00190018002300000016000000170000000d0030002e040305030603080708080809080a080b080408050806040105010601030302030301020103020202040205020602002b0009080304030303020301002d0002010100330026002400170020c7ba2d3c2543a66a3e1575dab429f61d3a0d6e680c83e86608330079d9c00b1c"));
        when(socket.getInputStream()).thenReturn(byteArrayInputStream);
        when(socket.getOutputStream()).thenReturn(byteArrayOutputStream);
        protocol = new TlsProtocol(socket, Certificate.EMPTY_CHAIN, pair.getPrivate(), 10);
        protocol.getContext().setTlsState(TlsState.START);
        protocol.stepConnectionState();
        assertEquals("TlsState is invalid", TlsState.RETRY_HELLO, protocol.getContext().getTlsState());
    }

    @Test
    @Category(de.rub.nds.praktikum.Aufgabe2.class)
    public void testInitSendHelloRetryRequest() throws Exception {
        socket = mock(Socket.class);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(new byte[0]);
        when(socket.getInputStream()).thenReturn(byteArrayInputStream);
        when(socket.getOutputStream()).thenReturn(byteArrayOutputStream);
        protocol = new TlsProtocol(socket, Certificate.EMPTY_CHAIN, pair.getPrivate(), 10);
        protocol.getContext().setTlsState(TlsState.RETRY_HELLO);
        List<CipherSuite> suiteList = new LinkedList<>();
        protocol.getContext().setClientCipherSuiteList(suiteList);
        protocol.getContext().setClientSessionId(Util.hexStringToByteArray("AABBCCDDEEFF0011AABBCCDDEEFF0011AABBCCDDEEFF0011AABBCCDDEEFF0011"));
        suiteList.add(CipherSuite.TLS_AES_128_GCM_SHA256);
        protocol.getContext().setClientCipherSuiteList(suiteList);
        protocol.stepConnectionState();
        assertEquals("TlsState is invalid", TlsState.AWAIT_RETRY_HELLO_RESPONSE, protocol.getContext().getTlsState());
    }

    @Test
    @Category(de.rub.nds.praktikum.Aufgabe2.class)
    public void testStepStatemachineWait_AwaitToRecievedClientHello() throws Exception {
        socket = mock(Socket.class);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Util.hexStringToByteArray("160301013c010001380303afe00859b6a927b382420cb225e5d23097f14e09c801c43eadce55d9d34d6552200e94608a60749bf7d2aa16be7b64db938ab697ee7c5ec6ddc8ae5de2c89a5ed0003e130213031301c02cc030009fcca9cca8ccaac02bc02f009ec024c028006bc023c0270067c00ac0140039c009c0130033009d009c003d003c0035002f00ff010000b10000000e000c0000096c6f63616c686f7374000b000403000102000a00160014001d0017001e0019001801000101010201030104002300000016000000170000000d0030002e040305030603080708080809080a080b080408050806040105010601030302030301020103020202040205020602002b0009080304030303020301002d00020101003300260024001d0020a87d9ff9e5c5ea38f3f11ce3663fe80023f6977bc44b5a8eb561161b5f3eaa22"));
        when(socket.getInputStream()).thenReturn(byteArrayInputStream);
        when(socket.getOutputStream()).thenReturn(byteArrayOutputStream);
        protocol = new TlsProtocol(socket, Certificate.EMPTY_CHAIN, pair.getPrivate(), 10);
        protocol.getContext().setTlsState(TlsState.AWAIT_RETRY_HELLO_RESPONSE);
        protocol.stepConnectionState();
        assertEquals("TlsState is invalid", TlsState.RECVD_CH, protocol.getContext().getTlsState());
    }


    @Test
    @Category(de.rub.nds.praktikum.Aufgabe2.class)
    public void testInitSessionClientHello() throws Exception {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Util.hexStringToByteArray("16030301320100012e0303d2070dda5da15b5b1e8df24392f06794436f684f4cde088fd852d7c0b6fdff4c20781bf656122613ab8dfdea009961ebe4bcacc71f1f5547c8a2f753273f2f68ad003e130213031301c02cc030009fcca9cca8ccaac02bc02f009ec024c028006bc023c0270067c00ac0140039c009c0130033009d009c003d003c0035002f00ff010000a70000000e000c0000096c6f63616c686f7374000b000403000102000a000c000a001d0017001e00190018002300000016000000170000000d0030002e040305030603080708080809080a080b080408050806040105010601030302030301020103020202040205020602002b0009080304030303020301002d00020101003300260024001d0020c7ba2d3c2543a66a3e1575dab429f61d3a0d6e680c83e86608330079d9c00b1c"));
        when(socket.getInputStream()).thenReturn(byteArrayInputStream);
        protocol = new TlsProtocol(socket, Certificate.EMPTY_CHAIN, pair.getPrivate(), 10);
        protocol.getContext().setTlsState(TlsState.START);
        protocol.stepConnectionState();
        assertEquals("TlsState is invalid", TlsState.RECVD_CH, protocol.getContext().getTlsState());
    }

    @Test
    @Category(de.rub.nds.praktikum.Aufgabe2.class)
    public void testInitSendServerHello() throws Exception {
        protocol = new TlsProtocol(socket, Certificate.EMPTY_CHAIN, pair.getPrivate(), 10);
        protocol.getContext().setTlsState(TlsState.RECVD_CH);
        List<CipherSuite> suiteList = new LinkedList<>();
        List<KeyShareEntry> keyShareEntryList = new LinkedList<>();
        keyShareEntryList.add(new KeyShareEntry(NamedGroup.ECDH_X25519.getValue(), Util.hexStringToByteArray("AABBCC00112200AABBCC00112200AABBCC00112200AABBCC0011220000001122")));
        protocol.getContext().setClientCipherSuiteList(suiteList);
        protocol.getContext().setKeyShareEntryList(keyShareEntryList);
        protocol.getContext().setClientSessionId(Util.hexStringToByteArray("AABBCCDDEEFF0011AABBCCDDEEFF0011AABBCCDDEEFF0011AABBCCDDEEFF0011"));
        suiteList.add(CipherSuite.TLS_AES_128_GCM_SHA256);
        protocol.getContext().setClientCipherSuiteList(suiteList);
        protocol.stepConnectionState();
        assertEquals("TlsState is invalid", TlsState.NEGOTIATED, protocol.getContext().getTlsState());
    }

    @Test
    @Category(de.rub.nds.praktikum.Aufgabe4.class)
    public void testInitFinished() throws Exception {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Util.hexStringToByteArray("1603030024140000203ec350bc78c95325caf5547ac4c4753b276ec2ae49dc0953173864cd7414a9df"));
        when(socket.getInputStream()).thenReturn(byteArrayInputStream);
        protocol = new TlsProtocol(socket, Certificate.EMPTY_CHAIN, pair.getPrivate(), 10);
        protocol.getContext().setClientHandshakeTrafficSecret(Util.hexStringToByteArray("c677a34f169db51f85411ddcacb9c461b603f72923d2a00dc918a915052e37a8"));
        protocol.getContext().setServerHandshakeTrafficSecret(Util.hexStringToByteArray("a0b47ba9e740d01c1da4960a174d79d03e71d178d18afa5f77a45cdcad3bff03"));
        protocol.getContext().setClientApplicationTrafficSecret(Util.hexStringToByteArray("0011"));
        protocol.getContext().setServerApplicationTrafficSecret(Util.hexStringToByteArray("2211"));
        protocol.getContext().setTlsState(TlsState.WAIT_FINISHED);
        KeyGenerator.adjustFinishedKeys(protocol.getContext());
        protocol.stepConnectionState();
        assertEquals(TlsState.CONNECTED, protocol.getContext().getTlsState());
    }

    @Test
    @Category(de.rub.nds.praktikum.Aufgabe4.class)
    public void testSendData() throws Exception {
        protocol = new TlsProtocol(socket, Certificate.EMPTY_CHAIN, pair.getPrivate(), 10);
        protocol.getContext().setTlsState(TlsState.CONNECTED);
        protocol.sendData(new byte[]{(byte) 0xFF});
        assertArrayEquals("The records is wrong", Util.hexStringToByteArray("1703030001FF"), outputStream.toByteArray());
    }

    @Test
    @Category(de.rub.nds.praktikum.Aufgabe4.class)
    public void testReceiveData() throws Exception {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Util.hexStringToByteArray("1703030001FF"));
        when(socket.getInputStream()).thenReturn(byteArrayInputStream);
        protocol = new TlsProtocol(socket, Certificate.EMPTY_CHAIN, pair.getPrivate(), 10);
        protocol.getContext().setTlsState(TlsState.CONNECTED);
        byte[] receivedData = protocol.receiveData();
        assertArrayEquals("Record data is invalid", Util.hexStringToByteArray("FF"), receivedData);
    }

    @Test(expected = TlsException.class)
    @Category(de.rub.nds.praktikum.Aufgabe4.class)
    public void testReceiveDataNonAppData() throws Exception {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Util.hexStringToByteArray("1603030001FF"));
        when(socket.getInputStream()).thenReturn(byteArrayInputStream);
        protocol = new TlsProtocol(socket, Certificate.EMPTY_CHAIN, pair.getPrivate(), 10);
        protocol.getContext().setTlsState(TlsState.CONNECTED);
        protocol.receiveData();
    }

    @Test(expected = TlsException.class)
    @Category(de.rub.nds.praktikum.Aufgabe4.class)
    public void testReceiveDataNotConnected() throws Exception {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Util.hexStringToByteArray("1603030001FF"));
        when(socket.getInputStream()).thenReturn(byteArrayInputStream);
        protocol = new TlsProtocol(socket, Certificate.EMPTY_CHAIN, pair.getPrivate(), 10);
        protocol.getContext().setTlsState(TlsState.START);
        protocol.receiveData();
    }

    @Test(expected = TlsException.class)
    @Category(de.rub.nds.praktikum.Aufgabe4.class)
    public void testSendDataNotConnected() throws Exception {
        protocol = new TlsProtocol(socket, Certificate.EMPTY_CHAIN, pair.getPrivate(), 10);
        protocol.getContext().setTlsState(TlsState.ERROR);
        protocol.sendData(new byte[]{(byte) 0xFF});
    }

    @Test
    @Category(de.rub.nds.praktikum.Aufgabe2.class)
    public void testStepStatemachineStartToRcvdClientHello() throws IOException {
        socket = mock(Socket.class);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Util.hexStringToByteArray("160301013c010001380303afe00859b6a927b382420cb225e5d23097f14e09c801c43eadce55d9d34d6552200e94608a60749bf7d2aa16be7b64db938ab697ee7c5ec6ddc8ae5de2c89a5ed0003e130213031301c02cc030009fcca9cca8ccaac02bc02f009ec024c028006bc023c0270067c00ac0140039c009c0130033009d009c003d003c0035002f00ff010000b10000000e000c0000096c6f63616c686f7374000b000403000102000a00160014001d0017001e0019001801000101010201030104002300000016000000170000000d0030002e040305030603080708080809080a080b080408050806040105010601030302030301020103020202040205020602002b0009080304030303020301002d00020101003300260024001d0020a87d9ff9e5c5ea38f3f11ce3663fe80023f6977bc44b5a8eb561161b5f3eaa22"));
        when(socket.getInputStream()).thenReturn(byteArrayInputStream);
        when(socket.getOutputStream()).thenReturn(byteArrayOutputStream);
        protocol = new TlsProtocol(socket, Certificate.EMPTY_CHAIN, pair.getPrivate(), 10);
        protocol.getContext().setTlsState(TlsState.START);
        protocol.stepConnectionState();
        assertEquals("TlsState is invalid", TlsState.RECVD_CH, protocol.getContext().getTlsState());
    }

    @Test
    @Category(de.rub.nds.praktikum.Aufgabe3.class)
    public void testStepStatemachineRcvdCHtoNegotiatedWithCrypto() throws IOException {
        //We do not send an actual certificate chain here. But this should work.
        protocol = new TlsProtocol(socket, Certificate.EMPTY_CHAIN, pair.getPrivate(), 10);
        protocol.getContext().setTlsState(TlsState.RECVD_CH);
        protocol.getContext().setClientCipherSuiteList(Arrays.asList(CipherSuite.TLS_AES_128_GCM_SHA256));
        protocol.getContext().setClientCompressions(Arrays.asList(CompressionMethod.NULL));
        protocol.getContext().setClientNamedGroupList(Arrays.asList(NamedGroup.ECDH_X25519));
        protocol.getContext().setClientRandom(new byte[32]);
        protocol.getContext().setClientSessionId(new byte[32]);
        protocol.getContext().setClientSupportedVersions(Arrays.asList(ProtocolVersion.TLS_1_3));
        protocol.getContext().setKeyShareEntryList(Arrays.asList(new KeyShareEntry(NamedGroup.ECDH_X25519.getValue(), new byte[32])));
        protocol.stepConnectionState();
        //Make sure to reset the SQN's in the record layer here
        assertEquals("TlsState is invalid", TlsState.NEGOTIATED, protocol.getContext().getTlsState());
        assertNotNull("ClientWriteIv must be set", protocol.getContext().getClientWriteIv());
        assertNotNull("ClientWriteKey must be set", protocol.getContext().getClientWriteKey());
        assertNotNull("EphemeralPrivateKey must be set", protocol.getContext().getEphemeralPrivateKey());
        assertNotNull("EphemeralPublicKey must be set", protocol.getContext().getEphemeralPublicKey());
        assertNotNull("HandshakeSecret must be set", protocol.getContext().getHandshakeSecret());
        assertNotNull("SelectedVersion must be set", protocol.getContext().getSelectedVersion());
        assertNotNull("ClientHandshakeTrafficSecret must be set", protocol.getContext().getClientHandshakeTrafficSecret());
        assertNotNull("ServerHandshakeTrafficSecret must be set", protocol.getContext().getServerHandshakeTrafficSecret());
        assertNotNull("EcdheSecret must be set", protocol.getContext().getSharedEcdheSecret());
    }

    @Test
    @Category(de.rub.nds.praktikum.Aufgabe4.class)
    public void testStepStatemachineNegotiatedToWait_Finished() throws IOException {
        protocol = new TlsProtocol(socket, Certificate.EMPTY_CHAIN, pair.getPrivate(), 10);
        protocol.getContext().setTlsState(TlsState.NEGOTIATED);
        protocol.getContext().setHandshakeSecret(new byte[32]);
        protocol.getContext().setServerHandshakeTrafficSecret(new byte[32]);
        protocol.getContext().setClientHandshakeTrafficSecret(new byte[32]);
        protocol.getContext().setSelectedSignatureAndHashAlgorithm(SignatureAndHashAlgorithm.ECDSA_SHA1);
        protocol.stepConnectionState();
        //Make sure to reset the SQN's in the record layer here
        assertEquals("TlsState is invalid", TlsState.WAIT_FINISHED, protocol.getContext().getTlsState());
        assertNotNull("MasterSecret must be set", protocol.getContext().getMasterSecret());
    }

    @Test
    @Category(de.rub.nds.praktikum.Aufgabe4.class)
    public void testStepStatemachineWait_FinishedToConnected() throws IOException {
        //We receive an unencrypted Finished message here, since we did not
        //enable encryption on the record layer. The verify data is correct.
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Util.hexStringToByteArray("160303002414000020af7220c93e5c02736bfc91499f00b1e2c939dda1e12f88e43e898489c94db4a5"));
        when(socket.getInputStream()).thenReturn(byteArrayInputStream);
        protocol = new TlsProtocol(socket, Certificate.EMPTY_CHAIN, pair.getPrivate(), 10);
        protocol.getContext().setTlsState(TlsState.WAIT_FINISHED);
        protocol.getContext().setSelectedCiphersuite(CipherSuite.TLS_AES_128_GCM_SHA256);
        protocol.getContext().setHandshakeSecret(new byte[32]);
        protocol.getContext().setServerApplicationTrafficSecret(new byte[32]);
        protocol.getContext().setClientApplicationTrafficSecret(new byte[32]);
        protocol.getContext().setClientFinishedKey(new byte[32]);
        protocol.stepConnectionState();
        //Make sure to reset the SQN's in the record layer here
        assertEquals("TlsState is invalid", TlsState.CONNECTED, protocol.getContext().getTlsState());
    }
    
        /**
     * Test if PassDataToLayer filters messages to the expected Layer
     */
    @Test
    @Category(de.rub.nds.praktikum.Aufgabe4.class)
    public void testParseChangeChipherSpecAndClientFinisedInOneStream() throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Util.hexStringToByteArray("140303000101160303002414000020af7220c93e5c02736bfc91499f00b1e2c939dda1e12f88e43e898489c94db4a5"));
        when(socket.getInputStream()).thenReturn(byteArrayInputStream);
        protocol = new TlsProtocol(socket, Certificate.EMPTY_CHAIN, pair.getPrivate(), 10);
        protocol.getContext().setTlsState(TlsState.WAIT_FINISHED);
        protocol.getContext().setSelectedCiphersuite(CipherSuite.TLS_AES_128_GCM_SHA256);
        protocol.getContext().setHandshakeSecret(new byte[32]);
        protocol.getContext().setServerApplicationTrafficSecret(new byte[32]);
        protocol.getContext().setClientApplicationTrafficSecret(new byte[32]);
        protocol.getContext().setClientFinishedKey(new byte[32]);
        protocol.stepConnectionState();
    }

    /**
     * Tests the transcript has computation described in RFC 8446, p. 63 4.4.1 -
     * "The Transcript Hash"
     *
     * @throws java.io.IOException
     */
    @Test
    @Category(de.rub.nds.praktikum.Aufgabe3.class)
    public void testTranscriptHash() throws IOException {
        final byte[] expectedTranscript = Util.hexStringToByteArray("f4023ac710e90d46582ddb5e05565699ebbbc349547795a70b07f3d3e278a6e0");
        Socket socket = mock(Socket.class);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Util.hexStringToByteArray("16030301320100012e0303d2070dda5da15b5b1e8df24392f06794436f684f4cde088fd852d7c0b6fdff4c20781bf656122613ab8dfdea009961ebe4bcacc71f1f5547c8a2f753273f2f68ad003e130213031301c02cc030009fcca9cca8ccaac02bc02f009ec024c028006bc023c0270067c00ac0140039c009c0130033009d009c003d003c0035002f00ff010000a70000000e000c0000096c6f63616c686f7374000b000403000102000a000c000a001d0017001e00190018002300000016000000170000000d0030002e040305030603080708080809080a080b080408050806040105010601030302030301020103020202040205020602002b0009080304030303020301002d0002010100330026002400170020c7ba2d3c2543a66a3e1575dab429f61d3a0d6e680c83e86608330079d9c00b1c"));
        when(socket.getInputStream()).thenReturn(byteArrayInputStream);
        when(socket.getOutputStream()).thenReturn(byteArrayOutputStream);
        TlsProtocol protocol = new TlsProtocol(socket, Certificate.EMPTY_CHAIN, null, 10);
        protocol.getContext().setTlsState(TlsState.START);
        protocol.stepConnectionState();
        assertEquals("TlsState is invalid", TlsState.RETRY_HELLO, protocol.getContext().getTlsState());
        assertArrayEquals("Digest is invalid", expectedTranscript, protocol.getContext().getDigest());
    }            
}
