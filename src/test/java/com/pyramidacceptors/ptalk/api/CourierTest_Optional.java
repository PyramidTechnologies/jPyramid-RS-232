package com.pyramidacceptors.ptalk.api;

import com.pyramidacceptors.ptalk.api.event.PTalkEvent;
import com.pyramidacceptors.ptalk.api.event.PTalkEventListener;
import org.junit.Test;

import org.junit.Before;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.verify;

/**
 * Test assumes you're slave is on port test.properties.test_port
 * Created by catix on 5/17/2015.
 */
public class CourierTest_Optional {


    @Mock
    EventMonitor monitor;

    private String testPort = "";

    /**
     * Initialize the mock monitor used for event testing
     */
    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);

        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = CourierTest_Optional.class.getClassLoader().getResource("test.properties").openStream();
            prop.load(input);

            testPort = prop.getProperty("test_port");

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if(testPort.length() == 0) {
            Logger.getAnonymousLogger().log(Level.ALL, "COM port not set in test.properties. Skipping test " +
            this.getClass().getCanonicalName());
        }

    }

    /**
     * This tests all of the function since they are so interconnected. You have
     * to register a listener to detect events and to verify that coomms are okay.
     * You also have to be running in order to simulate thread stops.
     * @throws Exception
     */
    @Test
    public void testRun() throws Exception {
        if(testPort.length() > 0) {
            PyramidPort port = new PyramidPort.PortBuilder(testPort).build();

            RS232Configuration.INSTANCE.setPollrate(50);
            Courier c = new Courier(port, new RS232Socket());


            // Not yet running, comms should be okay
            assertThat(true, is(c.getCommsOkay()));

            c.addChangeListener(monitor);

            // Run async
            c.start();

            ArgumentCaptor<PTalkEvent> captor = ArgumentCaptor.forClass(PTalkEvent.class);

            // This will verify that the event was fired within our monitor
            // Note, this is a virtual test and actually just checks the call stack so
            // don't expect anything to actually execute within "eventReceived".
            // There should be exactly one events for each of the Events in eventSet.
            verify(monitor).changeEventReceived(captor.capture());

            // Still running, comms should be okay
            assertThat(true, is(c.getCommsOkay()));

            EventMonitor monitorTwo = new EventMonitor();
            c.addChangeListener(monitorTwo);

            // TODO verify that these are detached
            c.removeChangeListener(monitor);
            c.removeChangeListeners();

            // Clean up and shutdown.
            c.stopThread();
            port.closePort();


        }

    }

    @Test
    public void testGetFirmwareRevision() throws Exception {
        RS232Packet packet;
        RS232Socket socket = new RS232Socket();

        packet= socket.parseResponse(xorBytewise(new byte[]{0x02, 0x0B, 0x20, 0x01, 0x10, 0x00, 0x00, 0x00, 0x00, 0x03}));
        assertTrue(packet.getFirmwareRevision() == (byte)0);

        packet = socket.parseResponse(xorBytewise(new byte[]{0x02, 0x0B, 0x20, 0x01, 0x10, 0x00, 0x00, 0x00, 0x01, 0x03}));
        assertTrue(packet.getFirmwareRevision() == (byte)1);

        packet = socket.parseResponse(xorBytewise(new byte[]{0x02, 0x0B, 0x20, 0x01, 0x10, 0x00, 0x00, 0x00, 0x7F, 0x03}));
        assertTrue(packet.getFirmwareRevision() == (byte)0x7F);
    }


    @Test
    public void testGetAcceptorModel() throws Exception {
        RS232Packet packet;
        RS232Socket socket = new RS232Socket();

        packet= socket.parseResponse(xorBytewise(new byte[]{0x02, 0x0B, 0x20, 0x01, 0x10, 0x00, 0x00, 0x00, 0x00, 0x03}));
        assertTrue(packet.getAcceptorModel() == (byte)0);

        packet = socket.parseResponse(xorBytewise(new byte[]{0x02, 0x0B, 0x20, 0x01, 0x10, 0x00, 0x00, 0x01, 0x00, 0x03}));
        assertTrue(packet.getAcceptorModel() == (byte)1);

        packet = socket.parseResponse(xorBytewise(new byte[]{0x02, 0x0B, 0x20, 0x01, 0x10, 0x00, 0x00, 0x7F, 0x0, 0x03}));
        assertTrue(packet.getAcceptorModel() == (byte)0x7F);
    }


    /**
     * Calculate 8-bit XOR on byte data skipping the STX and ETX bytes
     * @param packet
     * @return
     */
    private byte[] xorBytewise(byte[] packet) {
        byte[] result = new byte[packet.length+1];
        System.arraycopy(packet, 0, result, 0, packet.length);

        byte checksum = packet[1];
        for(int i=2; i<packet.length-1; i++) {
            checksum ^= packet[i];
        }
        result[result.length-1] = checksum;
        return result;
    }

    class EventMonitor implements RS232EventListener {
        @Override
        public void changeEventReceived(PTalkEvent evt) {

        }
    }
}