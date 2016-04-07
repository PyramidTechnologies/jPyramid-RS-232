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

import com.pyramidacceptors.ptalk.api.BillNames;

/**
 * Pyramid Technologies, Inc. 
 * Product: Pyramid API
 * Date: 14-07-2014 
 */

/**
 * Raised when slave enters the Escrowed state<br>
 * {@link Events#Escrowed}
 * @author <a href="mailto:cory@pyramidacceptors.com">Cory Todd</a>
 */
public class EscrowedEvent extends PTalkEvent {

    private final BillNames billName;

    /**
     * Creates a new event
     *
     * @param source     origin the event
     * @param rawMessage Stringified packet that generated this event
     */
    public EscrowedEvent(Object source, String rawMessage, BillNames billName)
    {
        super(source, Events.Escrowed, rawMessage);

        this.billName = billName;
    }

    public BillNames getBillName() { return this.billName; }

}