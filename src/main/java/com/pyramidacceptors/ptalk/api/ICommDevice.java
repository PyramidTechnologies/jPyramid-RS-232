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

/***
 * This interface describes the methods available to serial port consumers.<br>
 * The purpose of these methods is to provide a clean, safe, and concise <br>
 * set of commands consistent across any device that implements the underlying<br>
 * serial port.<br>
 * <br>
 * In the future, we will implement this interface in order to support <br>
 * printers, other acceptors, and any other device requiring serial <br>
 * communication.<br>
 * @author <a href="mailto:cory@pyramidacceptors.com">Cory Todd</a>
 * @since 1.0.0.0
 */
interface ICommDevice {
           
    /**
     * Return the poll rate for the given device. Depending on the interface 
     * this may default to a default value if not set by the client.
     * 
     * @return the poll rate in milliseconds
     */
    int getPollRate();
    
    /**
     * Set the poll rate for the given device. If the value is illegal, a<br>
     * boolean value of false will be returned. A valid poll rate is between<br>
     * 1 inclusive and 100 inclusive.
     * <br>
     * @param rate time in milliseconds at which polling will occur
     * @return boolean true is successful, false otherwise
     */
    boolean setPollRate(int rate);
    
    
    /**
     * @return true if the device is connected. Connected means the underlying<br>
     * port is open and the courier is running communications. True does not <br>
     * imply that all systems are operational.     
     */
    boolean isConnected();
    
    /**
     * After creating a new ICommDevice, connect() will attempt<br>
     * to open the underlying serial port connection and establish <br>
     * communications. <br>
     * <br>
     * @throws PyramidDeviceException if the port configuration is wrong for<br>
     * the selected device.
     */
    void connect() throws PyramidDeviceException;
    
     /**
     * After creating a new ICommDevice, disconnect() will attempt<br>
     * to close and flush the underlying serial port<br>
     * <br>
      * @throws com.pyramidacceptors.ptalk.api.PyramidDeviceException thrown if underlying port
      * cannot be opened.
     */
     void disconnect() throws PyramidDeviceException;
    
    
    /** @TODO Implement a status enumeration   <br>
     * Query the status of the attached ICommDevice<br>
     * <br>
     * @throws com.pyramidacceptors.ptalk.api.PyramidDeviceException thrown if underlying port
     * cannot be opened.
     */
    //public void getStatus() throws PyramidDeviceException;
    
    /**
     * Return the OS name of the connected port<br>
     * <br>
     * @return String name of port<br>
     * e.g. COM1 or /dev/term
     */
    String getPortName();
}
