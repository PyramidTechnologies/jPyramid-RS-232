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
 * Provides constant strings and enumerations used for API transactions<br>
 * between client code and the {@code ICommDevice}
 * <br>
 * @author Cory Todd <cory@pyramidacceptors.com>
 * @since 1.0.0.0
 */
public class APIConstants {
    
    /**
     * String response for client code
     */
    private static final String API_REVISION = "1.0.0.1";
    
    /**
     * Global, default timeout unless otherwise specified
     */
    public static final int COMM_TIMEOUT = 400;
    
    /**
     * These are all forwarded from the jSSC library
     */
    public static final int BAUDRATE_110 = 110;
    public static final int BAUDRATE_300 = 300;
    public static final int BAUDRATE_600 = 600;
    public static final int BAUDRATE_1200 = 1200;
    public static final int BAUDRATE_4800 = 4800;
    public static final int BAUDRATE_9600 = 9600;
    public static final int BAUDRATE_14400 = 14400;
    public static final int BAUDRATE_19200 = 19200;
    public static final int BAUDRATE_38400 = 38400;
    public static final int BAUDRATE_57600 = 57600;
    public static final int BAUDRATE_115200 = 115200;
    public static final int BAUDRATE_128000 = 128000;
    public static final int BAUDRATE_256000 = 256000;

    public static final int DATABITS_5 = 5;
    public static final int DATABITS_6 = 6;
    public static final int DATABITS_7 = 7;
    public static final int DATABITS_8 = 8;
    
    public static final int STOPBITS_1 = 1;
    public static final int STOPBITS_2 = 2;
    public static final int STOPBITS_1_5 = 3;
    
    public static final int PARITY_NONE = 0;
    public static final int PARITY_ODD = 1;
    public static final int PARITY_EVEN = 2;
    public static final int PARITY_MARK = 3;
    public static final int PARITY_SPACE = 4;     

    public static final int PURGE_RXABORT = 0x0002;
    public static final int PURGE_RXCLEAR = 0x0008;
    public static final int PURGE_TXABORT = 0x0001;
    public static final int PURGE_TXCLEAR = 0x0004;

    public static final int MASK_RXCHAR = 1;
    public static final int MASK_RXFLAG = 2;
    public static final int MASK_TXEMPTY = 4;
    public static final int MASK_CTS = 8;
    public static final int MASK_DSR = 16;
    public static final int MASK_RLSD = 32;
    public static final int MASK_BREAK = 64;
    public static final int MASK_ERR = 128;
    public static final int MASK_RING = 256;

    public static final int FLOWCONTROL_NONE = 0;
    public static final int FLOWCONTROL_RTSCTS_IN = 1;
    public static final int FLOWCONTROL_RTSCTS_OUT = 2;
    public static final int FLOWCONTROL_XONXOFF_IN = 4;
    public static final int FLOWCONTROL_XONXOFF_OUT = 8;
    
    public static final int ERROR_FRAME = 0x0008;
    public static final int ERROR_OVERRUN = 0x0002;
    public static final int ERROR_PARITY = 0x0004;

   
    
    /**
    * API Level assists in detecting how to talk to the target
    * {@code ICommDevice}
    * <br>
    * @author Cory Todd <cory@pyramidacceptors.com>
    * @since 1.0.0.0
    */
   public enum APILevel {
       /**
        * V1 devices are older and support standard RS-232 commands, nothing more
        */
       V1,
       /**
        * V2 devices support extended features TODO. V1 is a subset of V2
        */
       V2
   }
   
   /**
    * Enumerated bill directions specifically for a bill validator
    * type {@code ICommDevice}<br>
    * <br>
    * @author Cory Todd <cory@pyramidacceptors.com>
    * @since 1.0.0.0
    */
   public enum BillDirection {

        /**
         * No value has been assigned
         */
        Unset,

        /**
         * Front of bill, left side fed first
         */
        LeftUp,
        /**
         * Front of bill, right side fed first
         */
        RightUp,
        /**
         * Observe of bill, left side fed first
         */
        LeftDown,
        /**
         * Obverse of bill, right side fed first
         */
        RightDown;

        /**
         * Convert a byte into an enumerated bill direction <br>
         * <br>
         * @param b byte to convert
         * @return BillDirection enumeration. Invalid values default to 
         * {@code Unset}
         */
        public static BillDirection fromByte(byte b)
        {
            switch(b)
            {
                case 0:
                    return LeftUp;
                case 1:
                    return RightUp;
                case 2:
                    return LeftDown;
                case 3:
                    return RightDown;
                default:
                    return Unset;
            }
        }
    }
    
    /**
    * Enumerated bill enables for a bill validator {@code ICommDevice}.<br>
    * Pyramid designates up to twelve(12) slots for notes. <br>
    * RS-232 supports ONLY 7 notes.
    * <br>
    * @author Cory Todd <cory@pyramidacceptors.com>
    * @since 1.0.0.0
    */
   public enum BillNames {

       /**
        * No credit or non-credit value
        */
       Invalid,
       
       Bill1,
       Bill2,
       Bill3,
       Bill4,
       Bill5,
       Bill6,
       Bill7,
       Bill8,
       Bill9,
       Bill10,
       Bill11,
       Bill12;
       
       /**
         * Convert a byte into an enumerated bill name <br>
         * <br>
         * @param b byte to convert
         * @return BillDirection enumeration. <br>
         * Invalid values default to {@code Invalid}.
         */
        public static BillNames fromByte(byte b)
        {
            switch(b)
            {
                case 1:
                    return Bill1;
                case 2:
                    return Bill2;
                case 3:
                    return Bill3;
                case 4:
                    return Bill4;
                case 5:
                    return Bill5;
                case 6:
                    return Bill6;
                case 7:
                    return Bill7;
                case 8:
                    return Bill8;
                case 9:
                    return Bill9;
                case 10:
                    return Bill10;
                case 11:
                    return Bill11;
                case 12:
                    return Bill12;         
                    
                default:
                    return Invalid;
            }
        }
   }

    private APIConstants() {
    }

}
