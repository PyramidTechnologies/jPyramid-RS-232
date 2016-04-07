package com.pyramidacceptors.ptalk.api;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import org.junit.Test;

import java.util.Random;

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
        expected = "10 3A 02 03 04 05 06 07 08 09 08 ";
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
        byte[] data;
        RS232Packet packet;

        // Good input
        data = new byte[] { 0x45, 0x10, 0x22, 0x33, 0x44, 0x0E, 0x20, 0x37, 0x68, 0x19, 0x7F };
        packet = new RS232Packet(data);
        assertThat(true, is(packet.or(1, (byte)0x11)));
        assertThat((byte)0x11, is(packet.get(1)));

        // Too large of an index
        assertThat(true, is(not(packet.or(42, (byte) 0x45))));

        // Empty packet
        packet = new RS232Packet();
        assertThat(true, is(not(packet.or(-1, (byte)0x24))));

        // Overflow
        packet = new RS232Packet();
        assertThat(true, is(not(packet.or(Integer.MAX_VALUE, (byte)0x24))));

    }

    /**
     * Test that the parseAsNew function is returning various, expected
     * values. A lot of this function is dually tested in the RS232Socket tests.
     * @throws Exception
     */
    @Test
    public void testParseAsNew() throws Exception {
        byte[] data;
        String expected;
        RS232Packet packet;

        // Test that 0 sum packets return "Acceptor Bus/Not Connected"
        expected = RS232Packet.NO_CONNECTION;
        packet = new RS232Packet();
        data = new byte[] {0, 0, 0};
        assertThat(expected,is(packet.parseAsNew(data).getMessage()));

        // Empty packet
        packet = new RS232Packet();
        expected = RS232Packet.NO_CONNECTION;
        data = new byte[0];
        assertThat(expected,is(packet.parseAsNew(data).getMessage()));

        // Bad length
        packet = new RS232Packet();
        expected = RS232Packet.BAD_MESSAGE_LENGTH;
        data = new byte[] { 0x45, 0x10, 0x22, 0x33, 0x44, 0x0E, 0x20, 0x37, 0x68, 0x19, 0x7F, 0x09, 0x10 };
        assertThat(expected,is(packet.parseAsNew(data).getMessage()));

        // Bad checksum
        packet = new RS232Packet();
        expected = RS232Packet.BAD_CHECKSUM;
        data = new byte[] { 0x45, 0x10, 0x22, 0x33, 0x44, 0x0E, 0x20, 0x37, 0x68, 0x19, 0x7F};
        assertThat(expected,is(packet.parseAsNew(data).getMessage()));
    }

    /**
     * This just calls to String to verify that we
     * actually overrode it.
     * @throws Exception
     */
    @Test
    public void testToString() throws Exception {
        byte[] data;
        String expected;
        RS232Packet packet;

        // Create a packet which by default has an empty message field. Call toString
        // and we should just get back the raw string of what we instantiated the packet with.
        data = new byte[] {0x09};
        packet = new RS232Packet(data);
        expected = "Raw";
        assertThat(true, is(packet.toString().contains(expected)));

        // Create a packet and parse it to get the decoded message. toString
        // should have each of the following
        data = new byte[0];
        String[] expects = new String[] { "Response", "Event", "Message"};
        packet = new RS232Packet();
        packet.parseAsNew(data);
        for(String str : expects) {
            assertThat(true, is(packet.toString().contains(str)));
        }
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

        RS232Packet packet = new RS232Packet();
        RS232Packet clone = packet;
        RS232Packet anotherClone = clone;

        // Reflexivity - An object must equal itself.
        assertThat(packet, is(clone));

        // Symmetry
        assertThat(clone, is(packet));

        // Transitivity
        assertThat(anotherClone, is(packet));


        // Try it all over again but with a non-empty packet
        byte[] good = new byte[] { 0x10, 0x3A, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x08 };
        byte[] acceptThis = new byte[] {0x02, 0x0B, 0x20, 0x04, 0x00, 0x08, 0x00, 0x64, 0x64, 0x03, 0x27};
        packet = new RS232Packet(good);
        packet.parseAsNew(acceptThis);
        clone = packet;
        anotherClone = clone;
        assertThat(packet, is(clone));
        assertThat(clone, is(packet));
        assertThat(anotherClone, is(packet));


        // Non-instances must not be equal
        assertThat(true, is(not(packet.equals(RS232Packet.class))));

        // Unlike packets should not be equal
        packet = new RS232Packet(good);
        packet.or(1, (byte)0x06);
        assertThat(true, is(not(packet.equals(clone))));

        clone = new RS232Packet(acceptThis);
        assertThat(true, is(not(packet.equals(clone))));
    }

    /**
     * Prove that our hashcode is sufficiently efficient
     * @throws Exception
     */
    @Test
    public void testHashCode() throws Exception {
        int runFor = 256;
        int acceptableCollisions = 3;

        RS232Packet packet = new RS232Packet();
        final Multiset<Integer> set = HashMultiset.create();
        int hash;

        for(int i=0; i<runFor; i++) {
            packet.parseAsNew(generateRandomBytes(11));
            hash = packet.hashCode();
            set.add(hash);
            assertThat(true, is(not(set.count(hash) > acceptableCollisions)));
        }

        // Pass
        assertTrue(true);
    }

    private byte[] generateRandomBytes(int len) {
        byte[] out = new byte[len];
        Random rnd = new Random();
        rnd.nextBytes(out);
        return out;
    }
}