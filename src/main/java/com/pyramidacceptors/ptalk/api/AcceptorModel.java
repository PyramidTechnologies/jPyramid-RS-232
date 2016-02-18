package com.pyramidacceptors.ptalk.api;

/**
 * Acceptor Model enumeration as reported by Pyramid products. This will not be accurate
 * for other bill acceptor manufacturers.
 * Created by cory on 2/17/2016.
 */
public enum AcceptorModel {

    //0
    NotConnected("Not Connected"),

    //01
    Unknown("Unknown/Unsupported"), // Old firmware and models do not support this request and report 01

    //10
    Apex7400("Apex 7400"),
    //11
    Apex7600("Apex 7600"),
    //12
    Spectra("Spectra"),
    //13
    Trilogy("Trilogy");

    private final String name;

    private AcceptorModel(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return (otherName == null) ? false : name.equals(otherName);
    }

    public static AcceptorModel fromByte(byte b)
    {
        switch (b)
        {
            case 0:
                return AcceptorModel.NotConnected;

            case 2:
                return AcceptorModel.Trilogy;
            case 3:
                return AcceptorModel.Apex7400;
            case 4:
                return AcceptorModel.Apex7600;
            case 5:
                return AcceptorModel.Spectra;

            default:
                return AcceptorModel.Unknown;
        }
    }

    public String toString() {
        return this.name;
    }
}
