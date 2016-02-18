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

import static com.pyramidacceptors.ptalk.api.CreditActions.*;

import com.pyramidacceptors.ptalk.api.event.Events;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;

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
class RS232Packet {
    
    private final List<Byte> data = new ArrayList<>();    
    private final EnumSet<Events> event = EnumSet.noneOf(Events.class);
    
    private CreditActions creditAction = NONE;
    private String message = "";
    private BillNames billName;

    private byte firmwareRevision;
    private byte acceptorModel;

    public static final String NO_RESPONSE = "No Response";
    public static final String BAD_MESSAGE_LENGTH = "Bad Message Length";
    public static final String BAD_CHECKSUM = "Bad checksum";
    public static final String NO_CONNECTION = "Acceptor Bus/Not Connected";

    /**
     * Creates a new, empty RS232 packet
     */
    RS232Packet(){}
    
    /**
     * Create a new RS232 packet initialized with the byte array {@code bytes}
     * @param bytes
     * @throws IllegalAccessException when bytes is null
     */
    RS232Packet(byte[] bytes) {
        if(!(bytes instanceof byte[]))
            throw new IllegalArgumentException("bytes must not be null");
        for(byte b : bytes)
            data.add(b);
    }

    /**
     * Assert that this packet is of proper structure and matches<br>
     * checksum<br>.
     * <br>
     * @return true is valid, otherwise false
     */
    public boolean isValid() {
        if(data.size() != 11)
            return false;

        byte checksum = (byte)(data.get(1) ^ data.get(2));
        for(int i=3;i<9;i++) {
            checksum ^= data.get(i);
        }        
        return (checksum == data.get(10));     
    }

    /**
     * @return a space delimited string of the bytes in this packet
     */
    public String getByteString() {
        return byteListToString(data);
    }

    /**
     * @return byte[] of this packet in its current state
     */
    public byte[] toBytes() {
        byte[] o = new byte[data.size()];
        for(int i=0; i<data.size(); i++)
            o[i] = data.get(i);
        return o;
    }

    /**
     *  @return the length in bytes of this packet
     */    
    public int size() {
        return data.size();
    }

    /**
     * @return byte at given index
     */
    public byte get(int index) {
        return data.get(index);
    }

    /**
     * Performs checksum and any other enveloping required by <br>
     * the protocol.
     * @return RS232Packet a reference to this RS232Packet
     */
    public RS232Packet pack() {
        byte checksum = (byte)(data.get(1) ^ data.get(2));
        for(int i=3;i<data.size()-1;i++) {
            checksum ^= data.get(i);
        }        
        data.add(checksum);
        return this;
    }

    /**
     * Replaces the Object at {@code index} with the value {@code b}. If <br>
     * {@code index} is invalid or {@code b} is invalid, this returns false.
     * @param index of element to replace
     * @param b element to replace current item with
     * @return true on success
     */
    public boolean replace(int index, byte b) {
        if(index < 0 || index > (this.data.size()-1))
            return false;
        this.data.set(index, b);
        return true;
    }

    /**
     * Logical OR the byte at the given index
     * @param index of byte to OR
     * @param b byte to OR with
     * @return true on success
     */
    public boolean or(int index, byte b) {
        if(index < 0 || index > (this.data.size()-1))
            return false;
        byte o = this.data.get(index);
        this.data.set(index, (byte)(o | b));
        return true;
    }

    /**
     * Parse the given byte array as a new packet.
     * @param bytes to parse
     * @return new, fully parsed RS232Packet
     */
    public RS232Packet parseAsNew(byte[] bytes) {
        for(byte b : bytes)
            this.data.add(b);

        // Get handle on configuration
        RS232Configuration config = RS232Configuration.INSTANCE;
        // Do not process invalid messages, do not alter ACK if invalid
        if(sum(data) == 0) {
            
            // Don't modify the data to send (nextMsg)
            message = NO_CONNECTION;
          
        } else if((data.size() == 11) && isValid()) {
            
            // Message looks good, next one will toggle the ack
            config.toggleAck();
            
            // States - Only one allowed at a time
            byte t3 = data.get(3);
            if((t3 & 1) == 1)
                event.add(Events.Idling);
            else if ((t3 & 2) == 2)
                event.add(Events.Accepting);
            else if ((t3 & 4) == 4)
                event.add(Events.Escrowed);
            else if((t3 & 8) == 8)
                event.add(Events.Stacking);
            else if((t3 & 0x20) == 0x20)
                event.add(Events.Returning);
            
            if((t3 & 0x10) == 0x10) {
                event.add(Events.Idling);
                event.add(Events.Stacked);      // Be sure to clear the stack, return bits
            }
            if((t3 & 0x40) == 0x40){
                event.add(Events.Idling);
                event.add(Events.Returned);    // Be sure to clear the stack, return bits
            }

            // Events - There can be multiple
            byte t4 = data.get(4);
            if((t4 & 1) == 1)
                event.add(Events.Cheated);
            if((t4 & 2) == 2)
                event.add(Events.BillRejected);                
            if((t4 & 4) == 4)
                event.add(Events.BillJammed);                
            if((t4 & 8) == 8)             
                event.add(Events.StackerFull);                
            if((t4 & 0x10) == 0)       
                event.add(Events.BillCasetteRemoved);

            // Errors - there can be multiple
            byte t5 = (data.get(5));
            if((t5 & 1) == 1)
                event.add(Events.PowerUp);
            if((t5 & 2) == 2)
                event.add(Events.InvalidCommand);
            if((t5 & 4) == 4)
                event.add(Events.Failure);
            
            // Get the bill reported by the slave
            byte credit = (byte)((t5 & 0x38) >> 3);
            
            // Check if this bill is enabled. If so, send Accept,
            // otherwise send return
            if(credit != 0 && event.contains(Events.Escrowed)) {

                if( (config.getEnableMask() & (1 << credit - 1)) > 0)
                    creditAction = ACCEPT;
                else
                    creditAction = RETURN;    
            }
                
            //Check for a valid credit                      
            if(event.contains(Events.Stacked)) {
                event.add(Events.Credit);
                billName = BillNames.fromByte(credit);    
            }


            // Get model of acceptor
            acceptorModel = data.get(7);

            // Get revision of firmware
            firmwareRevision = data.get(8);

            // Else set the informative error message!
        } else {
            if(data.size() != 11)
                message = BAD_MESSAGE_LENGTH;
            else
                message = BAD_CHECKSUM;
        }
        
        return this;
    }

    /**
     * Returns the event, state, and message flags associated with this packet
     * @return EnumSet of all events enabled or signaled by this packet
     */
    public EnumSet<Events> getInterpretedEvents() {
        return this.event;
    }

    /**
     * Get any message associated with this packet as a string
     * @return string
     */
    public String getMessage() {
        if(this.message.equals("") && (billName != null))
            return this.billName.toString();
        else
            return this.message;
    }

    /**
     * Returns the firmware revision as reported by the slave. The revision
     * is in the format Major.minor. e.g. 1.11
     * @return string
     */
    public byte getFirmwareRevision() {
        return this.firmwareRevision;
    }

    /**
     * Return the model code as reported by the slave. The model is
     * an encoded AcceptorModel enum.
     * @return byte
     */
    public byte getAcceptorModel() {
        return this.acceptorModel;
    }

    /**
     * @return true is the parsed packet was a valid credit
     */
    public CreditActions getCreditAction() {
        return this.creditAction;
    }

    /**
     * @return the name of the bill credited. e.g. Bill1
     */
    public BillNames getBillName() {
        return this.billName;
    }

    /**
     * Integer Sum of all bytes in the bytes list
     * @param d
     * @return
     */
    private int sum(List<Byte> d) {
        Integer sum= 0;
        for (byte i:d)
            sum+=i;
        return sum;
    }

    /**
     * Create a new string from the given byte list.
     * @param l byte list to convert
     * @return string representation of byte list
     */
    private String byteListToString(List<Byte> l) {
        if (l == null || l.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Byte b : l) {
            sb.append(String.format(", 0x%02X", b));
        }
        String result = sb.toString();
        // We don't want that leading comma
        return result.substring(1, result.length()).trim();
    }

    /**
     * {@inheritDoc}
     */    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(message.equals("")) {
            sb.append(String.format("Command - Raw: %s", getByteString()));
        } else {
            sb.append(String.format("Response - Raw: %s", getByteString())); 
            sb.append(String.format("Event: %s", this.event.toString()));
            sb.append(String.format("Message: %s", this.message));
        }
        
        return sb.toString();
    }
    
    /**
     * {@inheritDoc}
     */    
    @Override
    public boolean equals(Object object) {
        if(!(object instanceof RS232Packet))
            return false;
        
        RS232Packet cmp = new RS232Packet(((RS232Packet)object).toBytes());
        if(data.size() != cmp.size())
            return false;
        
        for(int i=0; i<data.size(); i++) {
            if(data.get(i) != cmp.get(i))
                return false;
        }
        
        return true;
    }

    /**
     * {@inheritDoc}
     */    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + Objects.hashCode(this.data);
        hash = 43 * hash + Objects.hashCode(this.event);
        hash = 43 * hash + Objects.hashCode(this.message);
        return hash;
    }

}
