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

/**
 * Pyramid Technologies, Inc. 
 * Product: Pyramid API
 * Date: 16-07-2014 
 */

/**
 * Raised when slave raises a Reject event<br>
 * {@link Events#BillRejected}
 * @author Cory Todd <cory@pyramidacceptors.com>
 */
public class BillRejectedEvent extends PTalkEvent {
    private static final long serialVersionUID = 1L;

    /**
     * Raised when slave raises a Reject event
     * @param source origin the event
     * @param billName the name of the bill credited or invalid if non-credit event
     * @param friendylyString parsed command code or additional information
     * @param e EnumSet containing all events and the state of this PTalkEvent
     */    
    public BillRejectedEvent(Object source, BillNames billName, 
            String friendylyString, EnumSet<Events> e) {
        super(source, billName, friendylyString, e);
    }

    /**
     * Construct a this derived event from its base event
     * @param event PTalk parent event
     */
    public BillRejectedEvent(PTalkEvent event) {
        super(event.getSource(), event.getBillName(), 
                event.getFriendlyString(), event.getEventId());
    }    
}
