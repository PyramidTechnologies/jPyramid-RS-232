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

import jssc.SerialPortException;

/**
 * A PyramidDeviceException is thrown by any method that encounters as<br>
 * exceptional circumstance while interacting with device. <br>
 * <br>
 * @author Cory Todd <cory@pyramidacceptors.com>
 * @since 1.0.0.0
 */
public class PyramidDeviceException extends SerialPortException {
    
    /**
     * Standard message for unsupported devices detected by the API
     */
    public static final String DEVICE_IS_NOT_SUPPORTED =
            "Connected device is not supported";
    
    /**
    * Standard message for devices that require a firmware request to use a <br>
    * given feature
    */
    public static final String DEVICE_REQUIRES_UPDATE_FOR_THIS_FEATURE = 
            "Connected device requires a firwmare update to access this"
            + "feature"; 
    
    /**
     * A PyramidDeviceException is any exceptional situation caused by an <br>
     * illegal of a connected device
     * <br>
     * @param portName String name of the port connecting the slave device
     * @param methodName Name of method in which exception occurred
     * @param exceptionType Classification of exception TODO
     */
    public PyramidDeviceException(String portName, String methodName, 
            String exceptionType){
        super(portName, methodName, exceptionType);
    }   
    
}
