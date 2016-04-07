package com.pyramidacceptors.ptalk.api;

/**
 * Created by cory on 4/7/2016.
 */
public class Utilities {

    /**
     * Byte array to string including the 0X notation
     *
     * @param arr byte[]
     * @return String in the format 0x01 0x02 0x03, etc
     */
    public static String bytesToString(byte[] arr) {
        return bytesToString(arr, null);
    }

    /**
     * Byte array to string including the notation
     *
     * @param arr byte[]
     * @param fmt printf style format. Defaults to "%02X "
     * @return String in the format 0F B1 03, etc
     */
    public static String bytesToString(byte[] arr, String fmt) {

        if (fmt == null || fmt.length() == 0) {
            fmt = "%02X ";
        }


        StringBuilder sb = new StringBuilder(arr.length * 2);
        for (byte b : arr)
            sb.append(String.format(fmt, b & 0xff));
        return sb.toString();


    }
}
