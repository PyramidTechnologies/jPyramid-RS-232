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

import com.pyramidacceptors.ptalk.api.APIConstants.BillNames;
import java.util.EnumSet;
import java.util.EventObject;

/**
 * Consumers subscribe to these events for all device notifications. This event
 * may be a cast into a variety of events depending on the attached slave.<br>
 * Please see {@link Events} for more information.
 * <br>
 * Extend this class to create your events.<br>
 * @author <a href="mailto:cory@pyramidacceptors.com">Cory Todd</a>
 * @since 1.0.0.0
 */
public class PTalkEvent extends EventObject {
    
    private static final long serialVersionUID = 1L;
    
    private final EnumSet<Events> _eventSet;
    //private final String _rawBytes;
    private final String _friendlyString;
    private final BillNames _billName;
    
    /**
     * Base event for all PTalk events that a slave can raise
     * <br>
     * @param source origin the event
     * @param billName the name of the bill credited or invalid if non-credit event
     * @param friendylyString parsed command code or additional information
     * @param e Events type
     */
    public PTalkEvent(Object source, BillNames billName, 
            String friendylyString, EnumSet<Events> e) {
        super(source);          
        //this._rawBytes = rawBytes;
        this._billName = billName;
        this._friendlyString = friendylyString;
        this._eventSet = e;
    }
    
    /**
     * Event ID for acceptor event filters.
     * @return Events id
     */
    public EnumSet<Events> getEventId() { return this._eventSet; }
    
    /**    
     * @return the bill name credited or Invalid for non-credit events
     */
    public BillNames getBillName() {
        return this._billName;
    }
    
//    /**     
//     * @return the byte array response as a space delimited string
//     */
//    public String getRawByteString() {
//        return this._rawBytes;
//    }
    
    /**     
     * @return the human friendly response name. E.g. Idling, accepting, <br>
     * CassetteMissing
     */
    public String getFriendlyString() {
        return this._friendlyString;
    }
}
