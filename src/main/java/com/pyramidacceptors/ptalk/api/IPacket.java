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

import com.pyramidacceptors.ptalk.api.APIConstants.BillNames;
import com.pyramidacceptors.ptalk.api.RS232Socket.CreditActions;
import com.pyramidacceptors.ptalk.api.event.Events;
import java.util.EnumSet;

/**
 * Pyramid Technologies, Inc. 
 * Product: Pyramid API
 * Date: 15-07-2014 
 */

/**
 * Packet is a data structure that represents a serial byte stream. Packets <br>
 * have functions that include validation, packaging, and parsing.
 * 
 * @author <a href="mailto:cory@pyramidacceptors.com">Cory Todd</a>
 */
interface IPacket {
    
     /**
     * Performs checksums and any other enveloping required by <br>
     * the protocol. 
     * @return IPacket a reference to this IPacket
     */
    IPacket pack();    
    
    /**
     * Parse the given byte array as a new packet.
     * @param bytes to parse
     * @return new, fully parsed IPacket
     */
    IPacket parseAsNew(byte[] bytes);
    
    /**
     * Replaces the Object at {@code index} with the value {@code b}. If <br>
     * {@code index} is invalid or {@code b} is invalid, this returns false.
     * @param index of element to replace
     * @param b element to replace current item with
     * @return true on success
     */
    boolean replace(int index, byte b);
    
    /**
     * Logical OR the byte at the given index
     * @param index of byte to OR
     * @param b byte to OR with
     * @return true on success
     */
    boolean or(int index, byte b);    
    
    
    /**
     * There are three types of credit actions.
     * ACCEPT: Go ahead and issue credit
     * RETURN: The credit message was invalid
     * NONE: There are no credit messages to work with
     * @return credit action to execute
     */
    CreditActions getCreditAction();         
        
    /**
    * @return a space delimited string of the bytes in this packet
    */
    String getByteString();        
    
    /**     
     * @return the length in bytes of this packet
     */
    int size();    
    
    /**     
     * @return byte at given index
     */
    byte get(int index);
    
    /**
     * @return byte[] of this packet in its current state
     */
    byte[] toBytes();
                     
    /**
     * Assert that this packet is of proper structure and matches<br>
     * checksum<br>.
     * <br>
     * @return true is valid, otherwise false
     */
    boolean isValid();
   
    
    /**
     * Returns the event, state, and message flags associated with this packet
     * @return EnumSet of all events enabled or signaled by this packet
     */
    EnumSet<Events> getInterrpretedEvents();
    
    /**
     * Get any message associated with this packet as a string
     * @return string
     */
    String getMessage();   
    
    /**   
     * @return the name of the bill credited. e.g. Bill1
     */
    BillNames getBillName();
}
