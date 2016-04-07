package com.pyramidacceptors.ptalk.api;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by cory on 2/18/2016.
 */
public class AcceptorModelTest {

    @Test
    public void testEqualsName() throws Exception {
        assert(AcceptorModel.fromByte((byte)54).equals(AcceptorModel.UnsupportedDevice));
        assert(AcceptorModel.fromByte((byte)0x0E).equals(AcceptorModel.InvalidDevice));
    }

    @Test
    public void testFromByte() throws Exception {
        assert(AcceptorModel.fromByte((byte)0) == AcceptorModel.Apex52USA);
        assert(AcceptorModel.fromByte((byte)1) == AcceptorModel.Apex54USA);
        assert(AcceptorModel.fromByte((byte)2) == AcceptorModel.Apex56USA);

        assert(AcceptorModel.fromByte((byte)3) == AcceptorModel.Apex52FOR);
        assert(AcceptorModel.fromByte((byte)4) == AcceptorModel.Apex54FOR);
        assert(AcceptorModel.fromByte((byte)5) == AcceptorModel.Apex56FOR);

        assert(AcceptorModel.fromByte((byte)10) == AcceptorModel.UnknownDevice);

    }
}