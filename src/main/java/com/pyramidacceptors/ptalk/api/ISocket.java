/**
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

import com.pyramidacceptors.ptalk.api.event.PTalkEvent;

/***
 * This interface describes the methods made available to implementers<br>
 * of the socket. Sockets are used to transport data between a master and <br>
 * a slave. A Socket handles all handshaking and controls the flow of data.<br>
 * <br>
 * In the future this will be implemented for other interfaces such as<br>
 * ccTalk.<br>
 * <br>
 * @author <a href="mailto:cory@pyramidacceptors.com">Cory Todd</a>
 * @see com.pyramidacceptors.ptalk.api.PyramidPort
 * @see com.pyramidacceptors.ptalk.api.ICommDevice
 * @since 1.0.0.0
 */
public interface ISocket {
               
    /**
     * Acts as the transport layer of for the communication. This is <br>
     * responsible for generating and parsing all packets between master<br>
     * and slave according to the protocol.
     * <br>
     * @return byte[] form of message intended for slave
     */
    byte[] generateCommand();
    
    /**
     * Parse the message according to the protocol and set the <br>
     * appropriate state flags. The returned value is a PTalkEvent<br>
     * that can be returned directly to client code for parsing or <br>
     * passed off to an event filter for further refining.
     * <br>
     * @param bytes byte array response to parse
     * @return new PTalkEvent packed with parsed response data
     */
    PTalkEvent parseResponse(byte[] bytes);
    
    
    /**
     * Get the maximum size packet we would RECEIVE from a slave<br>
     * <br>
     * E.g. RS-232 would be 11 (bytes)<br>
     * <br>
     * @return Integer Maximum packet size
     */
    int getMaxPacketRespSize();
    
    
//    /**
//     * This socket maintains an internal buffer of the last 200 packets. <br>
//     * this total for all values sent and received.
//     * @return 
//     */
//    public CircularFifoQueue<IPacket> getDebugBuffer();
}
