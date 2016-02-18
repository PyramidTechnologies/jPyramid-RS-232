package com.pyramidacceptors.ptalk.api;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by cory on 2/18/2016.
 */
public class AcceptorModelTest {

    @Test
    public void testEqualsName() throws Exception {
        assert(AcceptorModel.Apex7400.equalsName(AcceptorModel.Apex7400.toString()));
        assert(AcceptorModel.Apex7600.equalsName(AcceptorModel.Apex7600.toString()));
        assert(AcceptorModel.Spectra.equalsName(AcceptorModel.Spectra.toString()));
        assert(AcceptorModel.Trilogy.equalsName(AcceptorModel.Trilogy.toString()));

    }

    @Test
    public void testFromByte() throws Exception {
        assert(AcceptorModel.fromByte((byte)0) == AcceptorModel.NotConnected);
        assert(AcceptorModel.fromByte((byte)1) == AcceptorModel.Unknown);

        assert(AcceptorModel.fromByte((byte)2) == AcceptorModel.Trilogy);
        assert(AcceptorModel.fromByte((byte)3) == AcceptorModel.Apex7400);
        assert(AcceptorModel.fromByte((byte)4) == AcceptorModel.Apex7600);
        assert(AcceptorModel.fromByte((byte)5) == AcceptorModel.Spectra);

        assert(AcceptorModel.fromByte((byte)10) == AcceptorModel.Unknown);

    }
}