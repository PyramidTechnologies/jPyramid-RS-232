package com.pyramidacceptors.ptalk.api;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by cory on 2/18/2016.
 */
public class AcceptorModelTest {

    @Test
    public void testFromByte() throws Exception {
        assert(AcceptorModel.fromByte((byte)0).equals(AcceptorModel.Apex52USA));
        assert(AcceptorModel.fromByte((byte)1).equals(AcceptorModel.Apex54USA));
        assert(AcceptorModel.fromByte((byte)2).equals(AcceptorModel.Apex56USA));

        assert(AcceptorModel.fromByte((byte)3).equals(AcceptorModel.Apex52FOR));
        assert(AcceptorModel.fromByte((byte)4).equals(AcceptorModel.Apex54FOR));
        assert(AcceptorModel.fromByte((byte)5).equals(AcceptorModel.Apex56FOR));

        assert(AcceptorModel.fromByte((byte)100).equals( AcceptorModel.UnknownDevice));

    }
}