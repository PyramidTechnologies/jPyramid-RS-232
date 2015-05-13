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
 * Pyramid Technologies, Inc. 
 * Product: Pyramid API
 * Date: 14-07-2014 
 */

/**
 * Configuration is intended to be a globally available configuration<br>
 * for the selected serial protocol. The methods within should be common<br>
 * to all configuration implementations. This would include settings<br>
 * such as bill enabled or disabled or what events to listen for.
 * 
 * @author <a href="mailto:cory@pyramidacceptors.com">Cory Todd</a>
 */
public interface IConfiguration {

    /**
     * Events are the foundation of the PTalk, event driven API. As a client,<br>
     * you are able to select which events (interface dependent) you want<br>
     * notification for.
     * The mask is a bitwise integer where 1 indicates active and indicates <br>
     * inactive.
     * @param mask 
     */
    //public void setEventMask(int mask);
    
    /**     
     * @return  the current event mask
     */
    int getEventMask();
    
    /**
     * Serial Protocol often allow for the enable/disable of bills or other <br>
     * features via a bit mask. This implementation supports an integer mask <br>
     * where 1 indicates the feature is enabled and 0 indicates that it is<br>
     * disabled.
     * @param mask bitwise enable/disable mask. Set bit position to 1 to enable, 0 to disable.
     */
    void setEnableMask(int mask);

    /**
     * Enable or disable the slave device if the protocol supports it.
     * @param enable true will enable the device, false will disable
     */
    void setEnabled(boolean enable);
    
    /**    
     * @return true if the device is enabled, otherwise false
     */
    boolean getEnabled();
}
