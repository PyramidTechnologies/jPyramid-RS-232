package com.pyramidacceptors.ptalk.api;

/**
* Enumerated bill enables for a bill validator {@code ICommDevice}.<br>
* Pyramid designates up to twelve(12) slots for notes. <br>
* RS-232 supports ONLY 7 notes.
* <br>
* @author <a href="mailto:cory@pyramidacceptors.com">Cory Todd</a>
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
