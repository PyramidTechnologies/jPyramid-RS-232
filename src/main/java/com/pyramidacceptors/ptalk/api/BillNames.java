package com.pyramidacceptors.ptalk.api;

/**
 * Enumerated bill enables for a bill validator {@code ICommDevice}.<br>
 * The RS-232 bill acceptor specification allows for up to 7 different<br>
 * bill values. In the USA for example, the values would be<br>
 * $1 <br>
 * $2 (Commonly disabled) <br>
 * $5<br>
 * $10<br>
 * $20<br>
 * $50<br>
 * $100
 * <br><br>
 *
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
    Bill7;

    /**
     * Convert a byte into an enumerated bill name <br>
     * <br>
     *
     * @param b byte to convert
     * @return BillDirection enumeration. <br>
     * Invalid values default to {@code Invalid}.
     */
    public static BillNames fromByte(byte b) {
        switch (b) {
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

            default:
                return Invalid;
        }
    }
}
