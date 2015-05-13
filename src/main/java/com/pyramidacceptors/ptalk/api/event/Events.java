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

package com.pyramidacceptors.ptalk.api.event;

/**
 * Pyramid Technologies, Inc. 
 * Product: Pyramid API
 * Date: 15-07-2014 
 */
/**
 * The bill acceptor will pass through a series of "States" during bill 
 * processing. The acceptor will always be in a " State." If the acceptor
 * is waiting for a bill insertion, it will report an "Idle" state to the
 * master. If the acceptor is reading a bill , i t will b e reporting
 * an "Accepting" state to the master. Only one state can be set at a 
 * time. The acceptor m a y also have a reported "Event" taking place.
 * For example, if the acceptor has just stacked a bill in the
 * "Stacking Mechanism", it will report a "Stacked" event and since it
 * is now waiting for another bill insertion, it will also report an
 * "Idle" state within the same message. It is possible for a multiple
 * event to be set in a message. Events are temporary. For example, when
 * a message sent by the acceptor has been received by the master and a
 * new message is then sent by the master, with a new MSG/ack number,
 * the acceptor should clear the  previous event bit that is set when
 * it sends its next response.
 * <img src="../../../../../doc-files/StatesVEvents.png" alt="RS-232 State Machine">
 * 
 * @author <a href="mailto:cory@pyramidacceptors.com">Cory Todd</a>
 * @since 1.0.0.0
 */
public enum Events {
    /**
     * Fall back event in the event that we know something happened but 
     * we're not quite sure what.
     */
    Generic(0xFF),
    /**
     * The bill acceptor has not processed a bill and is
     * waiting for a bill to be inserted. There are no problems
     * with the acceptor is this state.
     */    
    Idling(1),
    /**
     * In this state, a bill is being received through the acceptor. The 
     * bill has not reached the "Escrow" position yet. 
     */    
    Accepting(2),
    /**
     * The bill is valid and in sitting inside the bill acceptor. 
     * The "Escrow State" reports the bill value field and will indicate
     * the denomination of the bill. If the bill is invalid, the state
     * of escrow would never be reported. When the acceptor first powers
     * up and a bill is found to be in escrow, the acceptor would report
     * in an escrow state with the "Bill Value Field" set to 000
     * (unknown value). The master would then send a return message
     * to the acceptor. The acceptor finishes with a returned event,
     * then it goes back to the idle state.
     */    
    Escrowed(3),
    /**
     *  The acceptor remains in this state as it transports the bill 
     * from the escrow position toward a secure position past all the
     * bill acceptors internal sensors, as well as the stacking 
     * mechanism sensors. The acceptor will not change states until
     * the bill is stacked or jammed. During power up if a bill is not
     * in the escrow position, this state bit and the power up bit will
     * be reported.
     */    
    Stacking(4),
    /**
     * After the bill has been successfully stacked into the cashbox,
     * this event is reported. The master will then issues the credit
     * for the bill.
     */    
    Stacked(5),
    /**
     * This state is set for the purpose of returning a bill to the
     * patron. The master orders the bill to be returned because it
     * responded to an Escrowed message and did not want to accept this
     * bill. A "Returning" message and a "Rejected" message are quite
     * different. A returning message means that the master did not want
     * the acceptor to take a particular bill of valid currency. A
     * rejected message is sent because the acceptor found the bill
     * to be not valid.
     */    
    Returning(6),
    /**
      * When the master tells the slave to return a bill
      * (After an Escrowed message), this message is sent after the bill
      * has been successfully returned to the patron. This bill was valid
      * but for some reason, the master wanted it to be returned.
      */    
    Returned(7),
    /**
      * If the acceptor perceives a bill as being manipulated, this event
      * reports it. The bill may be returned to the patron, or be stacked
      * with no credit issued. (Bill Value Field = 000)
      */    
    Cheated(8),
    /**
     * A bill will be returned to the patron because the acceptor found
     * it to be invalid.
     */    
    BillRejected(9),
    /**
     * The acceptor can not stack a bill or return the bill due to an
     * error. The acceptor will keep sending this message until it has
     * stopped trying to complete the operation (stack or return). If
     * the jammed bill is removed, the acceptor will automatically exit
     * this state.
     */    
    BillJammed(10),
    /**
    * If this state has been reported, the acceptor can no longer
    * accept any bills. Usually, this indicates that the cashbox is full.
    */    
    StackerFull(11),
    /**
     * (controlled by the bill acceptor) When the cassette is present,
     * this bit will be set to a logic high. If there is no cassette, 
     * the bit will be cleared. Therefore, the acceptor will not accept
     * any bills. Note: not used on
     */    
    BillCasetteRemoved(12),
    /**
     * The unit is just powering up and is not ready to accept bills.
     */
    PowerUp(13),
    /**
     * An invalid command or message was sent by the slave.
     */
    InvalidCommand(14),
    /**
     * If this state has been reported, the acceptor is implying that
     * a condition exists that prevents the bill acceptor from accepting
     * any more currency. For example, a sensor may have failed, then
     * the acceptor will enter this state.
     */    
    Failure(15),
    /**
     * A valid credit message has been received.
     */
    Credit(16);
    
    private final int event;
    
    Events(int val){
        this.event = val;
    }
    
    /**     
     * @return the unique integer ID of this event. This may coincide with
     * with the ordinal number but ordinal should not be relied up. Use 
     * IntId for all type switching on this event.
     */
    public int getIntId() {
        return event;
    }
}
