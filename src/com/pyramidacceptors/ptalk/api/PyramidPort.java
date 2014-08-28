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

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import jssc.SerialPort;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import jssc.SerialPortList;
import jssc.SerialPortTimeoutException;

/***
 * A PyramidPort is an abstraction of {@code jssc.SerialPort}. This <br>
 * a set of wrapper functions that implement a serial port specifically<br>
 * configured for pyramid devices and protocols.<br>
 * <br>
 * @see jssc.SerialPort
 * @author Cory Todd <cory@pyramidacceptors.com>
 * @since 1.0.0.0
 */
final public class PyramidPort {
    
    private SerialPort _port;      
    private final String portName;    
    private final int baudRate;
    private final int dataBits;
    private final int parity;
    private final int stopBits;
    private final boolean setRTS;
    private final boolean setDTR;
  
    /***
     * PortBuilder is a helper class for configurating a <tt>PyramidPort</tt>.<br>
     * Be sure to call .build() once you have called all configuration <br>
     * parameters.<br>
     * <br>
     * @author Cory Todd <cory@pyramidacceptors.com>
     * @since 1.0.0.0
     */
     static class PortBuilder {
     
        // Required parameters
        private final String portName;

        // Optional parameters - initialized to default values
        private int baudRate        = SerialPort.BAUDRATE_9600;
        private int dataBits        = SerialPort.DATABITS_7;
        private int parity          = SerialPort.PARITY_EVEN;
        private int stopBits        = SerialPort.STOPBITS_1;
        private boolean setRTS      = true;
        private boolean setDTR      = true;

        /***
         * Apply required parameters to the builder<br>
         * <br>
         * @param portName Required - the string name of the port to operate
         */
        PortBuilder(String portName) {
            this.portName = portName;
        }
        /***
         * Sets the baud rate for this port <br>
         * Default: 9600<br>
         * <br>
         * @param val baud rate to set 
         * @return PortBuilder
         */
        PortBuilder baudRate(int val)
            { baudRate = val;      return this; }
        /***
         * Sets the number of databits for this port <br>
         * Default: 7<br>
         * <br>
         * @param val number of databits to set
         * @return PortBuilder
         */
        PortBuilder dataBits(int val)
            { dataBits = val;           return this; }
        /***
         * Set the parity for this port <br>
         * Default: Even<br>
         * <br>
         * @param val parity value to set
         * @return PortBuilder
         */
        PortBuilder parity(int val)
            { parity = val;  return this; }
        /***
         * Set the stopbits for this port <br>
         * Default: 1 <br>
         * <br>
         * @param val stopbits to set
         * @return PortBuilder
         */
        PortBuilder stopBits(int val)
            { stopBits = val;        return this; }
        /***
         * Set the RTS on or off for this port <br>
         * Default on<br>
         * <br>
         * @param val true enables RTS
         * @return PortBuilder
         */
        PortBuilder setRTS(boolean val)
            { setRTS = val; return this; }
         /***
         * Set the DTR on or off for this port <br>
         * Default on<br>
         * <br>
         * @param val true enables DTR
         * @return PortBuilder
         */
        PortBuilder setDTR(boolean val)
            { setDTR = val; return this; }
        /***
         * Build and return a new port<br>
         * <br>
         * @return PyramidPort
         * @throws PyramidDeviceException 
         */
        PyramidPort build() throws PyramidDeviceException {
            return new PyramidPort(this);
        }
    }

    private PyramidPort(PortBuilder builder) throws PyramidDeviceException {
        
        portName    = builder.portName;
        baudRate    = builder.baudRate;
        dataBits    = builder.dataBits;
        parity      = builder.parity;
        stopBits    = builder.stopBits;
        setRTS      = builder.setRTS;
        setDTR      = builder.setDTR;
        
        try {
           _port = new SerialPort(builder.portName);
           this.openPort();
           _port.setParams(baudRate, dataBits, stopBits, parity, setRTS, setDTR);  
           _port.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
        }
        catch(SerialPortException ex)
        {        
            Logger.getLogger(SerialPort.class.getName()).log(Level.SEVERE, null, ex);
            throw new PyramidDeviceException(ex.getPortName(), ex.getMethodName(), ex.getExceptionType());
        }
    }  
  
  /**
   * Open the underlying serial port. The port must be valid and recognized by the <br>
   * OS. If the port does not exist or is not recognized by the OS, this action<br>
   * will fail with an exception.<br>
   * <br>
   * @return true is successful, otherwise false
   * @throws PyramidDeviceException
   * @since 1.0.0.0
   */
  boolean openPort() throws PyramidDeviceException {
      try {
          return _port.openPort();
      } catch (SerialPortException ex) {
          Logger.getLogger(PyramidPort.class.getName()).log(Level.SEVERE, null, ex);
          throw new PyramidDeviceException(ex.getPortName(), ex.getMethodName(), ex.getExceptionType());
      }
  }
  
   /***
   * Close the underlying serial port. The port must be valid and recognized by the <br>
   * OS. If the port does not exist or is not recognized by the OS, this action<br>
   * will fail with an exception. If the port is already closed, the request is <br>
   * ignored.<br>
   * <br>
   * @return true is successful, otherwise false
   * @throws PyramidDeviceException
   * @since 1.0.0.0
   */
  boolean closePort() throws PyramidDeviceException {
      try {
          _port.purgePort(dataBits);
          return _port.closePort();
      } catch (SerialPortException ex) {
          Logger.getLogger(PyramidPort.class.getName()).log(Level.SEVERE, null, ex);
          throw new PyramidDeviceException(ex.getPortName(), ex.getMethodName(), ex.getExceptionType());
      }
  }
  
  /**
   * @return true if the serial port is open<br>
   */
  boolean isOpen() {
      return _port == null ? false : _port.isOpened();
  }
  
  
  /**
   * @return the OS's name for the given port<br>
   */
  String getPortName() {
      return _port == null ? "" : _port.getPortName();
  }
 
  
  /**
   * Read <tt>count</tt> bytes from this port.<br>
   * <br>
   * @param count number of bytes to read
   * @return byte[] of what is read
   * @throws jssc.SerialPortException
   * @throws jssc.SerialPortTimeoutException
   */
  byte[] readBytes(int count) throws SerialPortException, 
          SerialPortTimeoutException {
      return _port.readBytes(count, APIConstants.COMM_TIMEOUT);
  }
    
  /**
   * Read <tt>count</tt> number of bytes and return the value as a string <br>
   * delimited by <tt>delimeter</tt><br>
   * <br>
   * @param count number of bytes to read
   * @param delimeter string to separate bytes in return string
   * @return String byte array converted to string of bytes read
   * @throws jssc.SerialPortException
   */
  String readHexString(int count, String delimeter) throws SerialPortException {          
      return _port.readHexString(count, delimeter);
  }
    
    /**
   * Writes the byte[] implementation to the underlying serial port<br>
   * <br>
   * @param data byte array to write to port
   * @throws com.pyramidacceptors.ptalk.api.PyramidDeviceException
   */
  void write(byte[] data) throws PyramidDeviceException {
       try {
          _port.writeBytes(data);
      } catch (SerialPortException ex) {
          Logger.getLogger(PyramidPort.class.getName()).log(Level.SEVERE, null, ex);
          throw new PyramidDeviceException(ex.getPortName(), ex.getMethodName(), ex.getExceptionType());
      } 
  }
  
  /**
   * Register the SerialPortEventListener to event on this port.<br>
   * By default, this only listens for data Rx events. <br>
   * <br>
   * @param listener SerialPortEventListener the listen to register
   * @throws jssc.SerialPortException
   */
   void addEventListener(SerialPortEventListener listener) throws SerialPortException {
      _port.addEventListener(listener);
   }
   
     /**
   * Register the SerialPortEventListener to event on this port.<br>
   * By default, this only listens for data Rx events. Adjust mask<br>
   * to listen for other events.<br>
   * <br>
   * @param listener SerialPortEventListener the listen to register
   * @param mask Integer mask to apply on event listener
   * @throws jssc.SerialPortException
   */
   void addEventListener(SerialPortEventListener listener, int mask) throws SerialPortException {
      _port.addEventListener(listener, mask);
   }
   
   /**
    * Set events mask. Required flags shall be sent to the input. <br>
    * Variables with prefix "MASK_", shall be used as flags, for example <br>
    * "MASK_RXCHAR". Sent parameter "mask" is additive value, so addition <br>
    * of flags is allowed. For example if messages about data receipt and <br>
    * CTS and DSR status changing shall be received, it is required to set<br>
    * the mask - "MASK_RXCHAR | MASK_CTS | MASK_DSR"<br>
    *<br>
    * @param mask integer mask to apply to serial port event filter
    * @throws jssc.SerialPortException    
    */
   private void setEventsMask(int mask) throws SerialPortException {
       _port.setEventsMask(mask);
   }
    
  /**
   * Retrieve a list of ports available to the host OS<br>
   * <br>
   * @return String[] list of port names
   */
  public static String[] getPortList() {
      return SerialPortList.getPortNames();
  }
  
  /**
   * Retrieve a list of ports available on the given path (filter).<br>
   * <b>This is ignored on Windows</b><br>
   * For *nix style systems you may use a path such as "/dev" or "/dev/term"<br>
   * <br>
   * @param filter  string is the path to search for ports
   * @return String[] list of port names
   */
  public static String[] getPortList(String filter) {
      return SerialPortList.getPortNames(filter);
  }
  
  /**
   * Retrieve a list of ports with names matching the given Regex pattern <br>
   *<br>
   * @param pattern Regex to search for port names
   * @return String[] list of port names
   */
  public static String[] getPortList(Pattern pattern) {
      return SerialPortList.getPortNames(pattern);
  }
  
  /**
   * Retrieve a list of ports with names matching the given Regex pattern AND<br>
   * ports that reside on the given path.<br>
   * <b>This is ignored on Windows</b><br>
   * For *nix style systems you may use a path such as "/dev" or "/dev/term"<br>
   * <br>
   * @param pattern Regex pattern to search for port names   
   * @param filter String is the path to search for ports
   * @return String[] list of port names
   */
  public static String[] getPortList(Pattern pattern, String filter) {
      return SerialPortList.getPortNames(filter, pattern);
  }  
}
