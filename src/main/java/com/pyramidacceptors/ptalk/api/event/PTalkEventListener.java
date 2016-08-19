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
 * Implementers of this interface wish to subscribe to all
 * {@link PTalkEvent}. PTalkEvents include all state changes
 * and events raised by a slave device.
 * <br>
 *
 * @author <a href="mailto:cory@pyramidacceptors.com">Cory Todd</a>
 * @see PTalkEvent
 * @see Events
 * @since 1.0.0.0
 */
public interface PTalkEventListener {
    /**
     * Triggered when the event origin achieves an event
     * <br>
     *
     * @param evt event to push to listeners
     */
    void changeEventReceived(PTalkEvent evt);

}
