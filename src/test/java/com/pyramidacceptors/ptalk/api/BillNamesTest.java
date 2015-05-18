package com.pyramidacceptors.ptalk.api;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created by catix on 5/17/2015.
 */
public class BillNamesTest {

    @Test
    public void testFromByte() throws Exception {
        BillNames[] names = BillNames.values();
        for(byte i=0; i<names.length; i++) {
            assertThat(names[i], is(BillNames.fromByte(i)));
        }
    }
}