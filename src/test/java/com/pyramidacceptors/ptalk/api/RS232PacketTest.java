package com.pyramidacceptors.ptalk.api;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

/**
 * Created by catix on 5/16/2015.
 */
public class RS232PacketTest {

    /**
     * Prove that invalid packets are detected. Invalid packets
     * have the wrong length and/or the wrong checksum
     * @throws Exception
     */
    @Test
    public void testIsValid() throws Exception {
        byte[] bad;
        RS232Packet packet;

        // Empty
        bad = new byte[0];
        packet = new RS232Packet(bad);
        assertThat(true, is(not(packet.isValid())));

        // Too short
        bad = new byte[] { 0x00 };
        packet = new RS232Packet(bad);
        assertThat(true, is(not(packet.isValid())));

        // Too long
        bad = new byte[] { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0A, 0x0B };
        packet = new RS232Packet(bad);
        assertThat(true, is(not(packet.isValid())));

        // Bad checksum
        bad = new byte[] { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x68 };
        packet = new RS232Packet(bad);
        assertThat(true, is(not(packet.isValid())));

        // Good!
        bad = new byte[] { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x08 };
        packet = new RS232Packet(bad);
        assertThat(true, is(packet.isValid()));
    }

    /**
     * Prove that the returned hex string is what is expected
     * @throws Exception
     */
    @Test
    public void testGetByteString() throws Exception {
        byte[] bad;
        RS232Packet packet;
        String expected;

        // Good input
        bad = new byte[] { 0x10, 0x3A, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x08 };
        packet = new RS232Packet(bad);
        expected = "0x10, 0x3A, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x08";
        assertEquals(expected, packet.getByteString());

        // generate empty string
        bad = new byte[] {};
        packet = new RS232Packet(bad);
        expected = "";
        assertEquals(expected, packet.getByteString());

        // generate empty string
        packet = new RS232Packet();
        expected = "";
        assertEquals(expected, packet.getByteString());
    }

    /**
     * Prove that a packet cannot be instantiated with null input
     * @throws Exception
     */
    @Test(expected = Exception.class)
    public void testCreateNullPacket() throws Exception {
        RS232Packet packet = new RS232Packet(null);
    }

    /**
     * Prove the the packet is properly converted into a byte array
     * @throws Exception
     */
    @Test
    public void testToBytes() throws Exception {
        byte[] good;
        RS232Packet packet;

        // Good input
        good = new byte[] { 0x10, 0x3A, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x08 };
        packet = new RS232Packet(good);
        assertThat(good, is(packet.toBytes()));

        // Empty packet
        packet = new RS232Packet();
        assertThat(new byte[0], is(packet.toBytes()));
    }

    /**
     * Prove that the size is as expected
     * @throws Exception
     */
    @Test
    public void testSize() throws Exception {
        byte[] good;
        RS232Packet packet;

        // Good input
        good = new byte[] { 0x10, 0x3A, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x08 };
        packet = new RS232Packet(good);
        assertThat(good.length, is(packet.size()));

        // Empty packet
        packet = new RS232Packet();
        assertThat(0, is(packet.size()));
    }

    /**
     * Prove that the get at index function is correct
     * @throws Exception
     */
    @Test
    public void testGet() throws Exception {
        byte[] good = new byte[] { 0x10, 0x3A, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x08 };
        RS232Packet packet;

        // Good input
        packet = new RS232Packet(good);
        assertThat(good[1], is(packet.get(1)));
    }

    /**
     * Prove that the get at index function is correct
     * @throws Exception
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetOutOfBounds() throws Exception {
        byte[] good = new byte[] { 0x10, 0x3A, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x08 };
        RS232Packet packet;

        // Good input
        packet = new RS232Packet(good);
        assertThat(good[1], is(packet.get(-1)));
    }

    /**
     * Prove that a packet is packed as expected. That means that the
     * returned object has a valid checksum and is of the expected length.
     * @throws Exception
     */
    @Test
    public void testPack() throws Exception {

    }

    /**
     * Prove that the replace element at function is working
     * @throws Exception
     */
    @Test
    public void testReplace() throws Exception {
        byte[] good;
        RS232Packet packet;

        // Good input
        good = new byte[] { 0x10, 0x3A, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x08 };
        packet = new RS232Packet(good);
        assertThat(true, is(packet.replace(4, (byte)0x42)));
        assertThat((byte)0x42, is(packet.get(4)));

        // Too large of an index
        assertThat(true, is(not(packet.replace(100, (byte)0x45))));

        // Empty packet
        packet = new RS232Packet();
        assertThat(true, is(not(packet.replace(-1, (byte)0x24))));
    }

    /**
     * Prove that the OR element at index is working
     * @throws Exception
     */
    @Test
    public void testOr() throws Exception {

    }

    /**
     * Test that the parseAsNew function is returning various, expected
     * values. A lot of this function is dually tested in the RS232Socket tests.
     * @throws Exception
     */
    @Test
    public void testParseAsNew() throws Exception {

    }

    /**
     * This just calls to String to verify that we
     * actually overrode it.
     * @throws Exception
     */
    @Test
    public void testToString() throws Exception {

    }

    /**
     * Prove that our equals method does not violate the three equality rules:
     * 1) Reflexivity
     * 2) Symmetry
     * 3) Transitivity
     * @throws Exception
     */
    @Test
    public void testEquals() throws Exception {

    }

    /**
     * Prove that our hashcode is sufficiently efficient
     * @throws Exception
     */
    @Test
    public void testHashCode() throws Exception {

    }
}