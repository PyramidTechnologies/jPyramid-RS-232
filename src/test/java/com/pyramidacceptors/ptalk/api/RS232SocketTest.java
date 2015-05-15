package com.pyramidacceptors.ptalk.api;

import static com.pyramidacceptors.ptalk.api.event.Events.*;
import com.pyramidacceptors.ptalk.api.event.PTalkEvent;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by catix on 5/13/2015.
 */
public class RS232SocketTest {


    /**
     * This test generates a few commands while manipulating the RS232 singleton configurator.
     * After each command is generated, we check to see that it contains the expected values. Specifically,
     * we are looking for proper ACK number toggling, accept/reject messages, and escrow related messages.
     * @throws Exception
     */
    @Test
    public void testGenerateCommand() throws Exception {
        RS232Socket socket;

        byte[] actual, expected;

        // If another test ran before this toggled our ACK, be sure to clear it
        if(RS232Configuration.INSTANCE.getAck())
            RS232Configuration.INSTANCE.toggleAck();

        // On startup, non-escrow mode, the first slave message should always be
        socket = new RS232Socket();
        expected = new byte[]{ 0x02, 0x08, 0x10, 0x7F,  0x10,  0x00,  0x03, 0x77};
        actual = socket.generateCommand();
        assertArrayEquals(expected, actual);

        // On startup, escrow- mode, the first slave message should always be
        socket = new RS232Socket();
        RS232Configuration.INSTANCE.setEnabled(false);
        expected = new byte[]{ 0x02, 0x08, 0x10, 0x7F,  0x00,  0x00,  0x03, 0x67};
        actual = socket.generateCommand();
        assertArrayEquals(expected, actual);
        RS232Configuration.INSTANCE.setEnabled(true);

        // On startup, we begin with an "even" toggle number. Run through a few times and ensure that this
        // value is in fact toggling.
        socket = new RS232Socket();
        expected = new byte[]{ 0x02, 0x08, 0x10, 0x7F,  0x10,  0x00,  0x03, 0x77};
        actual = socket.generateCommand();
        assertArrayEquals(expected, actual);

        // Set to odd
        RS232Configuration.INSTANCE.toggleAck();

        expected = new byte[]{ 0x02, 0x08, 0x11, 0x7F,  0x10,  0x00,  0x03, 0x76};
        actual = socket.generateCommand();
        assertArrayEquals(expected, actual);

        // Set to even
        RS232Configuration.INSTANCE.toggleAck();

        expected = new byte[]{ 0x02, 0x08, 0x10, 0x7F,  0x10,  0x00,  0x03, 0x77};
        actual = socket.generateCommand();
        assertArrayEquals(expected, actual);

        // Do not toggle, we should still be even!
        actual = socket.generateCommand();
        assertArrayEquals(expected, actual);

        // Simulate an accept message
        socket = new RS232Socket();
        expected = new byte[]{ 0x02, 0x08, 0x10, 0x7F,  0x10,  0x00,  0x03, 0x77};

        // We have to simulate a slave message that would cause the master to generate an accept message
        // Set the escrow bit (4th byte, 3rd bit), credit bits (upper 5 bits of 5th byte)
        byte[] acceptThis = new byte[] {0x02, 0x0B, 0x20, 0x04, 0x00, 0x08, 0x00, 0x64, 0x64, 0x03, 0x27};

        socket.parseResponse(acceptThis);
        expected = new byte[]{ 0x02, 0x08, 0x11, 0x7F,  0x30,  0x00,  0x03, 0x56};
        actual = socket.generateCommand();
        assertArrayEquals(expected, actual);

        // Simulate a return message
        socket = new RS232Socket();
        expected = new byte[]{ 0x02, 0x08, 0x10, 0x7F,  0x10,  0x00,  0x03, 0x77};

        // We have to simulate a slave message that would cause the master to generate an accept message
        // Set the escrow bit (4th byte, 3rd bit), credit bits (upper 5 bits of 5th byte)
        // Disable bill in config (e.g. only enable the $10 bill)
        RS232Configuration.INSTANCE.setEnableMask(0x04);
        byte[] returnThis = new byte[] {0x02, 0x0B, 0x20, 0x04, 0x00, 0x08, 0x00, 0x64, 0x64, 0x03, 0x27};

        socket.parseResponse(returnThis);
        expected = new byte[]{ 0x02, 0x08, 0x10, 0x7F,  0x50,  0x00,  0x03, 0x37};
        actual = socket.generateCommand();
        assertArrayEquals(expected, actual);
    }

    /**
     * Send a variety of slave messages to and check that the master is generating the expecting
     * state and event messages.
     * @throws Exception
     */
    @Test
    public void testParseResponse() throws Exception {
        PTalkEvent event;
        byte[] parseThis;

        parseThis = new byte[] {0x02, 0x0B, 0x20, 0x01, 0x10, 0x00, 0x00, 0x00, 0x00, 0x03, 0x3A};
        event = new RS232Socket().parseResponse(parseThis);

        // There should only be 1 event and it should be Idling
        assertThat(true, is(event.getEventId().contains(Idling)));
        assertThat(1, is(event.getEventId().size()));

        parseThis = new byte[] {0x02, 0x0B, 0x20, 0x02, 0x10, 0x00, 0x00, 0x00, 0x00, 0x03, 0x39};
        event = new RS232Socket().parseResponse(parseThis);

        // There should only be 1 event and it should be Accepting
        assertThat(true, is(event.getEventId().contains(Accepting)));
        assertThat(1, is(event.getEventId().size()));
    }

    /**
     * Ensure that we are in fact returning the expected value, not a super class's value.
     * RS-232 slave packets should always have 11 bytes.
     * @throws Exception
     */
    @Test
    public void testGetMaxPacketRespSize() throws Exception {
        RS232Socket socket = new RS232Socket();
        assertThat(11, is(socket.getMaxPacketRespSize()));
    }
}