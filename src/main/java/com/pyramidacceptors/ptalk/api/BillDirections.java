package com.pyramidacceptors.ptalk.api;

/**
* Enumerated bill directions specifically for a bill validator
* type.
 *{@code ICommDevice}<br><br>
 * At this time, RS-232 does not support configuring these
 * options. Use Acceptor Tools or Blustream to configure this option.
 * <a href="https://pyramidacceptors.com/app">Download</a>
 *
* <br>
* @author <a href="mailto:cory@pyramidacceptors.com">Cory Todd</a>
* @since 1.0.0.0
*/
public enum BillDirections {

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
    public static BillDirections fromByte(byte b)
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
