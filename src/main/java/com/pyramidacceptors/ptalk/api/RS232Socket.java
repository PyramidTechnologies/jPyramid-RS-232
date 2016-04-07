/*
 * Copyright (C) 2014 Pyramid Technologies, Inc.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.pyramidacceptors.ptalk.api;


/**
 * A socket acts as the transport layer between a master and slave devce.<br>
 * This transport is responsible for tracking packets and calling the <br>
 * appropriate routines the protocol requires.
 * 
 * <a href="http://www.pyramidacceptors.com/files/RS_232.pdf">RS-232 Spec.</a>
 * @author <a href="mailto:cory@pyramidacceptors.com">Cory Todd</a>
 * @since 1.0.0.0
 */
final class RS232Socket {

    private static final int MAX_PACKET_SIZE = 11;  
    //# basic message           0      1     2      3       4      5      6      7
    //                         start, len,  ack,  bills, escrow, resv'd, end, checksum
    private final byte[] base = new byte[]{0x02, 0x08, 0x10, 0x7F,  0x10,  0x00,  0x03};

    /**
     * Generate a new RS-232 packet. By default, it is configured <br>
     * start with a standard polling message<br>
     */
    RS232Socket() {}

    /**
     * Generates the next master command
     * @param creditAction Credit action to take based upon last response
     * @return byte[] command to send to slave
     */
    public byte[] generateCommand(CreditActions creditAction) {

        RS232Packet packet;

        packet = new RS232Packet(base);
        if(RS232Configuration.INSTANCE.getAck())
            packet.replace(2, (byte)0x11);


        // Set enabled disable mask
        packet.replace(3, (byte)RS232Configuration.INSTANCE.getEnableMask());

        // Set the accept, return bits or clear them out
        switch(creditAction)
        {
            case ACCEPT:
                packet.or(4, (byte)0x20);
                break;
            case RETURN:
                packet.or(4, (byte)0x40);
                break;


            case NONE:
            default:
                packet.replace(4, (byte)0x00);
                break;
        }

        // Checksum it
        packet.pack();
        return packet.toBytes();
    }

    /**
     * Generates a non-standard command that is outside of the polling
     * loop.
     * @param content byte[] to insert into the master message. Must 5 or
     *                fewer bytes in length
     * @return byte[] command to send to slave
     */
    public byte[] generateCommandCustom(byte[] content) {
        RS232Packet packet = new RS232Packet(content);
        //if(RS232Configuration.INSTANCE.getAck())
        //    packet.or(2, (byte)0x01);
        packet.pack();
        return packet.toBytes();
    }

    public RS232Packet parseResponse(byte[] bytes) {
        return new RS232Packet().parseAsNew(bytes);
    }
    
    public int getMaxPacketRespSize() {
        return MAX_PACKET_SIZE;
    }

}
