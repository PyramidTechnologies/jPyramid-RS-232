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


    /**
     * Applies a left padding to the provided string. If the string is already count in length,
     * no change will be made. Otherwise, the pad character will be prepended until count length
     * is satisfied.
     * @param in String to left pad
     * @param count Minimum string length to achieve
     * @param pad Character to pad with
     * @return String
     */
    static String leftPadding(String in, int count, char pad) {
        if(in.length() >= count)
            return in;

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<count-in.length(); i++) {
            sb.append(pad);
        }

        for(char c : in.toCharArray()) {
            sb.append(c);
        }

        return sb.toString();
    }

}
