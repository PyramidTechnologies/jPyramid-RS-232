package com.pyramidacceptors.ptalk.api;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
/**
 * Created by catix on 5/17/2015.
 */
public class BillDirectionTest {

    @Test
    public void testFromByte() throws Exception {
        assertThat(BillDirections.LeftUp, is(BillDirections.fromByte((byte) 0)));
        assertThat(BillDirections.RightUp, is(BillDirections.fromByte((byte) 1)));
        assertThat(BillDirections.LeftDown, is(BillDirections.fromByte((byte) 2)));
        assertThat(BillDirections.RightDown, is(BillDirections.fromByte((byte) 3)));
        assertThat(BillDirections.Unset, is(BillDirections.fromByte((byte) -1)));
    }
}