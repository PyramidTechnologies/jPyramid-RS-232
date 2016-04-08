package com.pyramidacceptors.ptalk.api;

import java.util.Arrays;

/**
 * Acceptor Model enumeration as reported by Pyramid products. This will not be accurate
 * for other bill acceptor manufacturers.
 * Created by cory on 2/17/2016.
 * @since 1.2.3
 */
public class AcceptorModel {

    public static final String Apex52USA = "Apex 5200 USA";
    public static final String Apex54USA = "Apex 5400 USA";
    public static final String Apex56USA =         "Apex 5600 USA";
    public static final String Apex52FOR =         "Apex 5200 Foreign";
    public static final String Apex54FOR =         "Apex 5400 Foreign";
    public static final String Apex56FOR =             "Apex 5600 Foreign";
    public static final String Trilogy =             "Trilogy";
    public static final String Aqua =             "Aqua";
    public static final String Phoenix =             "Phoenix";
    public static final String Curve =             "Curve";
    public static final String Pro2 =             "Pro2";
    public static final String Unused =             "unused";
    public static final String Apex72USA =             "Apex 7200";
    public static final String Apex74USA =             "Apex 7400";
    public static final String Apex76USA =             "Apex 7600";
    public static final String Apex72FOR =             "Apex 7200";
    public static final String Apex74FOR =             "Apex 7400";
    public static final String Apex76FOR =             "Apex 7600";
    public static final String SpectraS4USA =             "Spectra S4";
    public static final String SpectraS6USA =             "Spectra S6";
    public static final String SpectraS4FOR =             "Spectra S4";
    public static final String SpectraS6FOR =             "Spectra S6";
    public static final String UnsupportedDevice = "Unsupported Device";
    public static final String UnknownDevice = "Unknown Device";
    public static final String InvalidDevice = "Invalid Device";

    /**
     * The values were never assigned a make for some reason
     */
    private static final byte[] INVALID_MAKES = new byte[]{
            0x0E, // unused
            0x0F  // unused
    };
    /**
     * These makes are not targeted for support in customer data tools
     */
    private static final byte[] UNSUPPORTED_MAKE = new byte[]{
            0x00, // Apex 5000
            0x01, // Apex 5000
            0x02, // Apex 5000
            0x03, // Apex 5000
            0x04, // Apex 5000
            0x05, // Apex 5000
            0x08, // Aqua
            0x09, // Aqua / Pro1
            0x0A, // Phoenix
            0x0B, // Phoenix
            0x0D  // Pro2
    };
    /**

    /**
     * Complete list of all models we have ever made
     */
    private static final String[] MAKE_NAME_LOOKUP_TABLE = new String[]{
            Apex52USA,
            Apex54USA,
            Apex56USA,

            Apex52FOR,
            Apex54FOR,
            Apex56FOR,

            Trilogy,
            Trilogy,

            Aqua,
            Aqua,

            Phoenix,
            Phoenix,

            Curve,

            Pro2,

            Unused,
            Unused,

            Apex72USA,
            Apex74USA,
            Apex76USA,

            Apex72FOR,
            Apex74FOR,
            Apex76FOR,

            SpectraS4USA,
            SpectraS6USA,

            SpectraS4FOR,
            SpectraS6FOR,
    };

    /**
     * Retrieve model name from lookup table using standard acctype code
     * @param model byte
     * @return String
     */
    public static String fromByte(byte model) {
        if (Arrays.asList(UNSUPPORTED_MAKE).contains(model)) {
            return UnsupportedDevice;
        }

        if (Arrays.asList(INVALID_MAKES).contains(model)) {
            return InvalidDevice;
        }
        if (model < MAKE_NAME_LOOKUP_TABLE.length) {
            return MAKE_NAME_LOOKUP_TABLE[model];
        }

        return UnknownDevice;
    }


}
