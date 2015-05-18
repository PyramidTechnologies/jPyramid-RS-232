package com.pyramidacceptors.ptalk.api;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
/**
 * Created by catix on 5/17/2015.
 */
public class RS232ConfigurationTest {

    @Test
    public void testGetEventMask() throws Exception {
        assertThat(0xFF, is(RS232Configuration.INSTANCE.getEventMask()));

        RS232Configuration.INSTANCE.setEventMask(0x43);
        assertThat(0x43, is(RS232Configuration.INSTANCE.getEventMask()));

        // Set back to default
        RS232Configuration.INSTANCE.setEventMask(0xFF);
    }
}