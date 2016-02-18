package com.pyramidacceptors.ptalk.api;

import static com.pyramidacceptors.ptalk.api.event.Events.*;

import com.pyramidacceptors.ptalk.api.event.Events;
import com.pyramidacceptors.ptalk.api.event.PTalkEvent;
import org.junit.Test;

import java.util.HashMap;

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
        actual = socket.generateCommand(CreditActions.NONE);
        assertArrayEquals(expected, actual);

        // On startup, escrow- mode, the first slave message should always be
        socket = new RS232Socket();
        RS232Configuration.INSTANCE.setEnabled(false);
        expected = new byte[]{ 0x02, 0x08, 0x10, 0,  0x10,  0x00,  0x03, 0x08};
        actual = socket.generateCommand(CreditActions.NONE);
        assertArrayEquals(expected, actual);
        RS232Configuration.INSTANCE.setEnabled(true);

        // On startup, we begin with an "even" toggle number. Run through a few times and ensure that this
        // value is in fact toggling.
        socket = new RS232Socket();
        expected = new byte[]{ 0x02, 0x08, 0x10, 0x7F,  0x10,  0x00,  0x03, 0x77};
        actual = socket.generateCommand(CreditActions.NONE);
        assertArrayEquals(expected, actual);

        // Set to odd
        RS232Configuration.INSTANCE.toggleAck();

        expected = new byte[]{ 0x02, 0x08, 0x11, 0x7F,  0x10,  0x00,  0x03, 0x76};
        actual = socket.generateCommand(CreditActions.NONE);
        assertArrayEquals(expected, actual);

        // Set to even
        RS232Configuration.INSTANCE.toggleAck();

        expected = new byte[]{ 0x02, 0x08, 0x10, 0x7F,  0x10,  0x00,  0x03, 0x77};
        actual = socket.generateCommand(CreditActions.NONE);
        assertArrayEquals(expected, actual);

        // Do not toggle, we should still be even!
        actual = socket.generateCommand(CreditActions.NONE);
        assertArrayEquals(expected, actual);

        // Simulate an accept message
        socket = new RS232Socket();
        expected = new byte[]{ 0x02, 0x08, 0x10, 0x7F,  0x10,  0x00,  0x03, 0x77};

        // We have to simulate a slave message that would cause the master to generate an accept message
        // Set the escrow bit (4th byte, 3rd bit), credit bits (upper 5 bits of 5th byte)
        byte[] acceptThis = new byte[] {0x02, 0x0B, 0x20, 0x04, 0x00, 0x08, 0x00, 0x64, 0x64, 0x03, 0x27};

        socket.parseResponse(acceptThis);
        expected = new byte[]{ 0x02, 0x08, 0x11, 0x7F,  0x30,  0x00,  0x03, 0x56};
        actual = socket.generateCommand(CreditActions.ACCEPT);
        assertArrayEquals(expected, actual);

        // Simulate a return message
        socket = new RS232Socket();
        expected = new byte[]{ 0x02, 0x08, 0x10, 0x7F,  0x10,  0x00,  0x03, 0x77};

        // We have to simulate a slave message that would cause the master to generate an accept message
        // Set the escrow bit (4th byte, 3rd bit), credit bits (upper 5 bits of 5th byte)
        // Disable bill in config (e.g. only enable the $10 bill)
        RS232Configuration.INSTANCE.setEnableMask(0x04);
        byte[] returnThis = new byte[] {0x02, 0x0B, 0x20, 0x04, 0x00, 0x08, 0x00, 0x64, 0x64, 0x03, 0x27};

        RS232Packet resp = socket.parseResponse(returnThis);
        CreditActions creditAction = resp.getCreditAction();
        expected = new byte[]{ 0x02, 0x08, 0x10, 0x04,  0x50,  0x00,  0x03, 0x4C};
        actual = socket.generateCommand(creditAction);
        assertArrayEquals(expected, actual);
    }

    /**
     * Test that the idle state is correctly parsed
     * @throws Exception
     */
    @Test
    public void testIdleState() throws Exception {
        PTalkEvent event;
        RS232Packet respPacket;
        byte[] parseThis;

        // There should only be 1 event and it should be Idling
        parseThis = xorBytewise(new byte[]{0x02, 0x0B, 0x20, 0x01, 0x10, 0x00, 0x00, 0x00, 0x00, 0x03});

        respPacket = new RS232Socket().parseResponse(parseThis);
        event = new PTalkEvent(
                this,
                respPacket.getBillName(),
                respPacket.getMessage(),
                respPacket.getInterpretedEvents());

        assertThat(true, is(event.getEventId().contains(Idling)));
        assertThat(1, is(event.getEventId().size()));
    }

    /**
     * Test that the accepting state is correctly parsed
     * @throws Exception
     */
    @Test
    public void testAcceptingState() throws Exception {
        PTalkEvent event;
        RS232Packet respPacket;
        byte[] parseThis;

        // There should only be 1 event and it should be Accepting
        parseThis = xorBytewise(new byte[]{0x02, 0x0B, 0x20, 0x02, 0x10, 0x00, 0x00, 0x00, 0x00, 0x03});

        respPacket = new RS232Socket().parseResponse(parseThis);
        event = new PTalkEvent(
                this,
                respPacket.getBillName(),
                respPacket.getMessage(),
                respPacket.getInterpretedEvents());

        assertThat(true, is(event.getEventId().contains(Accepting)));
        assertThat(1, is(event.getEventId().size()));
    }

    /**
     * Test that the escrowed state is correctly parsed
     * @throws Exception
     */
    @Test
    public void testEscrowedState() throws Exception {
        PTalkEvent event;
        RS232Packet respPacket;
        byte[] parseThis;

        // There should only be 1 event and it should be Escrowed
        parseThis = xorBytewise(new byte[]{0x02, 0x0B, 0x20, 0x04, 0x10, 0x00, 0x00, 0x00, 0x00, 0x03});
        respPacket = new RS232Socket().parseResponse(parseThis);
        event = new PTalkEvent(
                this,
                respPacket.getBillName(),
                respPacket.getMessage(),
                respPacket.getInterpretedEvents());

        assertThat(true, is(event.getEventId().contains(Escrowed)));
        assertThat(1, is(event.getEventId().size()));
    }

    /**
     * Test that the stacking state is correctly parsed
     * @throws Exception
     */
    @Test
    public void testStackingState() throws Exception {
        PTalkEvent event;
        RS232Packet respPacket;
        byte[] parseThis;

        // There should only be 1 event and it should be Stacking
        parseThis = xorBytewise(new byte[]{0x02, 0x0B, 0x20, 0x08, 0x10, 0x00, 0x00, 0x00, 0x00, 0x03});
        respPacket = new RS232Socket().parseResponse(parseThis);
        event = new PTalkEvent(
                this,
                respPacket.getBillName(),
                respPacket.getMessage(),
                respPacket.getInterpretedEvents());

        assertThat(true, is(event.getEventId().contains(Stacking)));
        assertThat(1, is(event.getEventId().size()));
    }

    /**
     * Test that the stacked event is correctly parsed.
     * There should be three events, stacked, idling, and credit. Credit sounds counter-intuitive
     * but consider that the stacked message from the slave is _only_ possible once the accepting,
     * escrow, and accept message transactions have proceeded without fault. This will log
     * a crediting invalid message (invalid credit value specified)
     * @throws Exception
     */
    @Test
    public void testStackedEvent() throws Exception {
        PTalkEvent event;
        RS232Packet respPacket;
        byte[] parseThis;

        parseThis = xorBytewise(new byte[]{0x02, 0x0B, 0x20, 0x11, 0x10, 0x00, 0x00, 0x00, 0x00, 0x03});
        respPacket = new RS232Socket().parseResponse(parseThis);
        event = new PTalkEvent(
                this,
                respPacket.getBillName(),
                respPacket.getMessage(),
                respPacket.getInterpretedEvents());

        assertThat(true, is(event.getEventId().contains(Stacked)));
        assertThat(true, is(event.getEventId().contains(Idling)));
        assertThat(true, is(event.getEventId().contains(Credit)));
        assertThat(3, is(event.getEventId().size()));
    }

    /**
     * Test that the returning state is correctly parsed
     * @throws Exception
     */
    @Test
    public void testReturningState() throws Exception {
        PTalkEvent event;
        RS232Packet respPacket;
        byte[] parseThis;

        // There should only be 1 event and it should be Returning
        parseThis = xorBytewise(new byte[]{0x02, 0x0B, 0x20, 0x20, 0x10, 0x00, 0x00, 0x00, 0x00, 0x03});
        respPacket = new RS232Socket().parseResponse(parseThis);
        event = new PTalkEvent(
                this,
                respPacket.getBillName(),
                respPacket.getMessage(),
                respPacket.getInterpretedEvents());

        assertThat(true, is(event.getEventId().contains(Returning)));
        assertThat(1, is(event.getEventId().size()));
    }

    /**
     * Test that the returned event is correctly parsed. Note that returned
     * is technically an event and is reported along with the idling state.
     * @throws Exception
     */
    @Test
    public void testReturnedEvent() throws Exception {
        PTalkEvent event;
        RS232Packet respPacket;
        byte[] parseThis;

        // There should be two events, returned and Idling
        parseThis = xorBytewise(new byte[]{0x02, 0x0B, 0x20, 0x41, 0x10, 0x00, 0x00, 0x00, 0x00, 0x03});
        respPacket = new RS232Socket().parseResponse(parseThis);
        event = new PTalkEvent(
                this,
                respPacket.getBillName(),
                respPacket.getMessage(),
                respPacket.getInterpretedEvents());

        assertThat(true, is(event.getEventId().contains(Returned)));
        assertThat(true, is(event.getEventId().contains(Idling)));
        assertThat(2, is(event.getEventId().size()));

    }

    /**
     * Test that the power up event is correctly parsed.
     * @throws Exception
     */
    @Test
    public void testPowerUpEvent() throws Exception {
        PTalkEvent event;
        RS232Packet respPacket;
        byte[] parseThis;

        // There should one event, power up
        parseThis = xorBytewise(new byte[]{0x02, 0x0B, 0x20, 0x00, 0x10, 0x01, 0x00, 0x00, 0x00, 0x03});
        respPacket = new RS232Socket().parseResponse(parseThis);
        event = new PTalkEvent(
                this,
                respPacket.getBillName(),
                respPacket.getMessage(),
                respPacket.getInterpretedEvents());

        assertThat(true, is(event.getEventId().contains(PowerUp)));
        assertThat(1, is(event.getEventId().size()));

    }

    /**
     * Test that the invalid command event is correctly parsed.
     * @throws Exception
     */
    @Test
    public void testInvalidCommandEvent() throws Exception {
        PTalkEvent event;
        RS232Packet respPacket;
        byte[] parseThis;

        // There should one event, power up
        parseThis = xorBytewise(new byte[]{0x02, 0x0B, 0x20, 0x00, 0x10, 0x02, 0x00, 0x00, 0x00, 0x03});
        respPacket = new RS232Socket().parseResponse(parseThis);
        event = new PTalkEvent(
                this,
                respPacket.getBillName(),
                respPacket.getMessage(),
                respPacket.getInterpretedEvents());

        assertThat(true, is(event.getEventId().contains(InvalidCommand)));
        assertThat(1, is(event.getEventId().size()));

    }

    /**
     * Test that the failure event is correctly parsed.
     * @throws Exception
     */
    @Test
    public void testFailureEvent() throws Exception {
        PTalkEvent event;
        RS232Packet respPacket;
        byte[] parseThis;

        // There should one event, power up
        parseThis = xorBytewise(new byte[]{0x02, 0x0B, 0x20, 0x00, 0x10, 0x04, 0x00, 0x00, 0x00, 0x03});
        respPacket = new RS232Socket().parseResponse(parseThis);
        event = new PTalkEvent(
                this,
                respPacket.getBillName(),
                respPacket.getMessage(),
                respPacket.getInterpretedEvents());

        assertThat(true, is(event.getEventId().contains(Failure)));
        assertThat(1, is(event.getEventId().size()));
    }

    /**
     * Test that the cashbox removed event is correctly parsed.
     * @throws Exception
     */
    @Test
    public void testCashboxRemovedEvent() throws Exception {
        PTalkEvent event;
        RS232Packet respPacket;
        byte[] parseThis;

        // There should one event, power up
        parseThis = xorBytewise(new byte[]{0x02, 0x0B, 0x20, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x03});
        respPacket = new RS232Socket().parseResponse(parseThis);
        event = new PTalkEvent(
                this,
                respPacket.getBillName(),
                respPacket.getMessage(),
                respPacket.getInterpretedEvents());

        assertThat(true, is(event.getEventId().contains(BillCasetteRemoved)));
        assertThat(1, is(event.getEventId().size()));
    }

    /**
     * Test that the credit events are correctly parsed. Check that all expected events
     * are returned and that the value of the credit event has the expected value.
     * @throws Exception
     */
    @Test
    public void testCreditEvents() throws Exception {
        PTalkEvent event;
        RS232Packet respPacket;
        byte[] parseThis;

        for(int credit=1; credit<8; credit++) {
            parseThis = xorBytewise(new byte[]{0x02, 0x0B, 0x20, 0x11, 0x10, (byte)(credit<<3), 0x00, 0x00, 0x00, 0x03});
            respPacket = new RS232Socket().parseResponse(parseThis);
            event = new PTalkEvent(
                    this,
                    respPacket.getBillName(),
                    respPacket.getMessage(),
                    respPacket.getInterpretedEvents());

            assertThat(true, is(event.getEventId().contains(Stacked)));
            assertThat(true, is(event.getEventId().contains(Idling)));
            assertThat(true, is(event.getEventId().contains(Credit)));
            assertThat(credit, is(event.getBillName().ordinal()));
            assertThat(3, is(event.getEventId().size()));
        }
    }

    /**
     * Test that all possible event/state combination yield the expected event.
     * @throws Exception
     */
    @Test
    public void testEventParse() throws Exception {
        PTalkEvent event;
        RS232Packet respPacket;

        //////
        //
        //  Event checks. There are 4 events that we want to validate here.
        int eventMask = 1;
        HashMap<Integer, Events> eventMap = new HashMap<Integer, Events>() {{
            put(1, Cheated);
            put(2, BillRejected);
            put(4, BillJammed);
            put(8, StackerFull);
        }};

        for (int i = 0; i < 4; i++) {

            // Cheat event
            byte[] base = new byte[]{0x02, 0x0B, 0x20, 0x01, (byte) (0x10 | eventMask),
                    0x00, 0x00, 0x00, 0x00, 0x03};

            // Simulate a cheat event during each state. Keep making copies of the cheatBase
            // which we modify to cover each state bit.
            for (int j = 0; j < 7; j++) {

                // Inside the loop because we want temp to be the same length every time, not constantly
                // incrementing by one.
                byte[] temp = new byte[base.length];

                System.arraycopy(base, 0, temp, 0, base.length);

                respPacket = new RS232Socket().parseResponse(xorBytewise(temp));
                event = new PTalkEvent(
                        this,
                        respPacket.getBillName(),
                        respPacket.getMessage(),
                        respPacket.getInterpretedEvents());

                assertThat(true, is(event.getEventId().contains(eventMap.get(eventMask))));

                // Left shift to get next state
                base[3] <<= 1;

            }


            base = new byte[]{0x02, 0x0B, 0x20, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x03};
            // Simulate a cheat event during each event. Keep making copies of the cheatBase
            // which we modify to cover each state bit.
            for (int k = 0; k < 32; k++) {
                // Always set the cheated bit
                base[4] |= eventMask;

                // Inside the loop because we want temp to be the same length every time, not constantly
                // incrementing by one.
                byte[] temp = new byte[base.length];

                System.arraycopy(base, 0, temp, 0, base.length);
                respPacket = new RS232Socket().parseResponse(xorBytewise(temp));
                event = new PTalkEvent(
                        this,
                        respPacket.getBillName(),
                        respPacket.getMessage(),
                        respPacket.getInterpretedEvents());

                assertThat(true, is(event.getEventId().contains(eventMap.get(eventMask))));

                base[4]++;

            }

            // Move on the next mask
            eventMask <<= 1;
        }
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
}