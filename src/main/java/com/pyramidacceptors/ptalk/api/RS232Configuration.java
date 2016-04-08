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

import com.pyramidacceptors.ptalk.api.event.Events;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * RS-232 states, events and errors as defined in the 
 * <a href="http://www.pyramidacceptors.com/files/RS_232.pdf">RS-232 Spec.</a><br> 
 * <br> 
 * @author <a href="mailto:cory@pyramidacceptors.com">Cory Todd</a>
 * @since 1.0.0.0
 */
public enum RS232Configuration {

    /**
     *Instance is singleton of RS232Configuration. This contains<br>
     * all state, event, and error information pertaining to the attached slave.
     */
    INSTANCE;

    private static final int POLL_RATE_MAX = 5000;     // Set max to 5 seconds

    /**
     * All flags except serial data event are enabled by default
     */
    public static final int DEFAULT_EVENT_MASK = 0xFFFE_FFFF;
    public static final int ALL_EVENTS_MASK = 0xFFFF_FFFF;

    private boolean _ack = false;
    private final AtomicBoolean _escrowmode 
            = new AtomicBoolean(false);         // Set to false to disable escrow
    private int _edMask = 0x7F;                 // All bills enabled by default
    private int _edMask_bak = 0x7F;             // Backup mask in case user uses setEnabled(false)
    private int _evMask = DEFAULT_EVENT_MASK;   // All events enabled by default
    private int _pollRate = 100;                // Default poll rate is milliseconds
    private int _poll_retry_limit = 5;          // Limit to number of failures polling loop will allow before
                                                // raising connection failure events

    /**
     * Returns the current poll rate is milliseconds. This is the rate at which
     * the master will query the slave.
     * @return int
     */
    public int getPollrate() {
        return _pollRate;
    }

    /**
     * Sets the pollrate in . his is the rate at which
     * the master is querying the slave.
     * @param pollrate integer poll rate
     * @return true if poll rate was succesfully set
     */
    public boolean setPollrate(int pollrate) {
        if(pollrate < 0 || pollrate > POLL_RATE_MAX)
            return false;

        this._pollRate = pollrate;
        return true;
    }

    /**
     * Returns the number of retries the polling loop will allow
     * before giving up and raising a connection failure events. Once this
     * limit is reached, the polling thread will raise a connection failure event
     * until either the polling loop is stopped or the target comes back online.
     * @return int
     */
    public int getPollRetryLimit() {
        return _poll_retry_limit;
    }

    /**
     * Sets the polling retry limit for the polling loop. This limit
     * determines how many communication failures (e.g. non-responsive, IOExceptions, etc.)
     * before connection falilure events begin being emitted. The limit must be >= 0.
     *
     * Default: 5
     *
     * @param limit int
     * @return boolean true of limit was applied successfully. False is limit is invalid.
     */
    public boolean setPollRetryLimit(int limit) {
        if(limit < 0)
            return false;
        this._poll_retry_limit = limit;
        return true;
    }

    /**    
     * @return true if ACK requires toggling
     */
    boolean getAck() { return this._ack; }
    /**
     * Track that the ACK has been toggled
     */
    void toggleAck() { this._ack = !this._ack; }

    /**
     * @return  the current event mask
     */
    public int getEventMask() {
        return this._evMask;
    }
    
    /**     
     * @return the current mask used for enabling or disabling acceptance <br>
     * of notes.
     */
    public int getEnableMask() {
        return this._edMask;
    }

    /**
     * Events are the foundation of the PTalk, event driven API. As a client,<br>
     * you are able to select which events (interface dependent) you want<br>
     * notification for.
     * The mask is a bitwise integer where 1 indicates active and indicates <br>
     * inactive.
     * @param mask  bitwise event mask
     * @see com.pyramidacceptors.ptalk.api.RS232Configuration.RS232State for bit indicies
     */
    public void setEventMask(int mask) {
       this._evMask = mask;
    }

    /**
     * Serial Protocol often allow for the enable/disable of bills or other <br>
     * features via a bit mask. This implementation supports an integer mask <br>
     * where 1 indicates the feature is enabled and 0 indicates that it is<br>
     * disabled.
     * @param mask bitwise enable/disable mask. Set bit position to 1 to enable, 0 to disable.
     */
    public void setEnableMask(int mask) {
        this._edMask = this._edMask_bak = mask;
    }


    /**
     * Enables or disables the bill acceptor. If disabled. the master will still be able to poll
     * and receive status messages from the slave but acceptance is disabled.
     * @param enabled true to enable, false to disable
     */
    public void setEnabled(boolean enabled)
    {
        this._edMask = (enabled) ? this._edMask_bak : 0;
    }


    /**
     * Enable or disable escrow mode on the slave device. This puts the host
     * in charge of giving the final accept/reject command.
     *
     * This feature is not correctly implemented so it has been disabled. It was originally
     * based off of a broken VB implementation which did not align with the specs.
     * @param enable true will enable the device, false will disable
     */
    public void setEscrowMode(boolean enable) {
        /*
        this._escrowmode.set(enable);
        */
    }

    /**
     * @return true if the device is in escrow mode, otherwise false
     */
    public boolean getEscrowMode() {
        return this._escrowmode.get();
    }    
    
    /**
     * The bill acceptor will pass through a series of "States" during bill <br>
     * processing. The acceptor will always be in a "State." If the acceptor<br>
     * is waiting for a bill insertion, it will report an "Idle" state to the<br>
     * master. If the acceptor is reading a bill , it will be reporting<br>
     * an "Accepting" state to the master. Only one state can be set at a <br>
     * time. The acceptor may also have a reported "Event" taking place.<br>
     * For example, if the acceptor has just stacked a bill in the<br>
     * "Stacking Mechanism", it will report a "Stacked" event and since it<br>
     * is now waiting for another bill insertion, it will also report an<br>
     * "Idle" state within the same message. It is possible for multiple<br>
     * events to be set in a message. Events are temporary. For example, when<br>
     * a message sent by the acceptor has been received by the master and a<br>
     * new message is then sent by the master, with a new MSG/ack number,<br>
     * the acceptor should clear the previous event bit that is set when<br>
     * it sends its next response.    
     */
    public enum RS232State implements IState {    

        /**
         * The unit is connected but not enabled
         */
        Offline,
        /**
         * The unit is online and operating normally
         */
        Online,

        /**
         * The bill acceptor has not processes a bill and is<br>
         * waiting for a bill to be inserted. There are no problems<br>
         * with th acceptor is this state is present.
         */
        Idling,
        /**
         * In this state, a bill is being received through the acceptor. The <br>
         * bill has not reached the "Escrow" position yet. <br>
         */
        Accepting,
        /**
         * The bill is valid and in sitting inside the bill acceptor. <br>
         * The "Escrow State" reports the bill value field and will indicate<br>
         * the denomination of the bill. If the bill is invalid, the state<br>
         * of escrow would never be reported. When the acceptor first powers<br>
         * up and a bill is found to be in escrow, the acceptor would report<br>
         * in an escrow state with the "Bill Value Field" set to 000<br>
         * (unknown value). The master would then send a return message<br>
         * to the acceptor. The acceptor finishes with a returned event,<br>
         * then it goes back to the idle state.
         */
        Escrowed,
        /**
         *  The acceptor remains in this state as it transports the bill <br>
         * from the escrow position toward a secure position past all the<br>
         * bill acceptors internal sensors, as well as the stacking <br>
         * mechanism sensors. The acceptor will not change states until<br>
         * the bill is stacked or jammed. During power up if a bill is not<br>
         * in the escrow position, this state bit and the power up bit will<br>
         * be reported.
         */
        Stacking,
        /**
         * This state is set for the purpose of returning a bill to the<br>
         * patron. The master orders the bill to be returned because it<br>
         * responded to an Escrowed message and did not want to accept this<br>
         * bill. A "Returning" message and a "Rejected" message are quit<br>
         * different. A returning message means that the master did not want<br>
         * the acceptor to take a particular bill of valid currency. A<br>
         * rejected message is sent because the acceptor found the bill<br>
         * to be not valid.
         */
        Returning,
        /**
         * The acceptor can not stack a bill or return the bill due to an<br>
         * error. The acceptor will keep sending this message until it has<br>
         * stopped trying to complete the operation (stack or return). If<br>
         * the jammed bill is removed, the acceptor will automatically exit<br>
         * this state.
         */
        Jammed,
        /**
         * If this state has been reported, the acceptor can no longer<br>
         * accept any bills. Usually, this indicates that the cashbox is full.
         */
        CashboxFull,
        /**
         * If this state has been reported, the acceptor is implying that<br>
         * a condition exists that prevents the bill acceptor from accepting<br>
         * any more currency. For example, a sensor may have failed, then<br>
         * the acceptor will enter this state.
         */
        Failure,
        /**
         * Any message sent by the slave that is not a valid message including
         * a credit value of (000).
         */
        InvalidMessage,
        
        //Note: Events are reported only once to the master.
        /**
         * After the bill has been successfully stacked into the cashbox,<br>
         * this event is reported. The master will then issues the credit<br>
         * for the bill.
         */
        Stacked,
        /**
         * When the master tells the slave to return a bill<br>
         * (After an Escrowed message), this message is sent after the bill<br>
         * has been successfully returned to the patron. This bill was valid<br>
         * but for some reason, the master wanted it to be returned.
         */
        Returned,
        /**
         * If the acceptor perceives a bill as being manipulated, this event<br>
         * reports it. The bill may be returned to the patron, or be stacked<br>
         * with no credit issued. (Bill Value Field = 000)
         */
        Cheated,
        /**
         * A bill will be returned to the patron because the acceptor found<br>
         * it to be invalid.
         */
        Rejected,
        
        // Other messages
        /**
         * (controlled by the bill acceptor) When the cassette is present,<br>
         * this bit will be set to a logic high. If there is no cassette, <br>
         * the bit will be cleared. Therefore, the acceptor will not accept<br>
         * any bills. Note: not used on
         */
        CassetePresent,
    }
    
}
