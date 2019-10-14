package de.rub.nds.praktikum.protocol;

import de.rub.nds.praktikum.constants.AlertDescription;
import de.rub.nds.praktikum.constants.AlertLevel;
import de.rub.nds.praktikum.constants.TlsState;
import de.rub.nds.praktikum.util.Util;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 *
 */
public class AlertLayerTest {

    private AlertLayer layer;
    private ByteArrayOutputStream outputStream;
    private ByteArrayInputStream inputStream;
    private SessionContext context;

    @Before
    public void setUp() {
        context = new SessionContext(null, null);
        outputStream = new ByteArrayOutputStream();
        inputStream = new ByteArrayInputStream(new byte[]{02, 14});
        RecordLayer recordLayer = new RecordLayer(outputStream, inputStream, context, 0);
        layer = new AlertLayer(context, recordLayer);
    }

    @Test
    @Category(de.rub.nds.praktikum.Aufgabe1.class)
    public void testSendAlert() throws Exception {
        layer.sendAlert(AlertLevel.WARNING, AlertDescription.BAD_RECORD_MAC);
        assertArrayEquals(outputStream.toByteArray(), Util.hexStringToByteArray("15030300020114"));
        layer.sendAlert(AlertLevel.FATAL, AlertDescription.BAD_RECORD_MAC);
        assertArrayEquals(outputStream.toByteArray(), Util.hexStringToByteArray("1503030002011415030300020214"));
    }

    @Test
    @Category(de.rub.nds.praktikum.Aufgabe2.class)
    public void testSendAlertTask2() throws Exception {
        layer.sendAlert(AlertLevel.WARNING, AlertDescription.BAD_RECORD_MAC);
        assertArrayEquals(outputStream.toByteArray(), Util.hexStringToByteArray("15030300020114"));
        assertNotEquals("Warning alerts should be fine", TlsState.ERROR, context.getTlsState());
        layer.sendAlert(AlertLevel.FATAL, AlertDescription.BAD_RECORD_MAC);
        assertArrayEquals(outputStream.toByteArray(), Util.hexStringToByteArray("1503030002011415030300020214"));
        assertEquals("Warning fatal alerts should move us to the error state", TlsState.ERROR, context.getTlsState());
    }

    @Category(de.rub.nds.praktikum.Aufgabe1.class)
    @Test
    public void testProcessByteStream() {
        layer.processByteStream(Util.hexStringToByteArray("0114"));
        //this is just a warning alert
        assertNotEquals(TlsState.ERROR, context.getTlsState());
        layer.processByteStream(Util.hexStringToByteArray("0214"));
        assertEquals(TlsState.ERROR, context.getTlsState());

    }

}
