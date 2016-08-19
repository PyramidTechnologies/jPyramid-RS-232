package com.pyramidacceptors.ptalk.api;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by cory on 4/8/2016.
 */
public class UtilitiesTest {

    @Test
    public void testLeftPadding() throws Exception {

        String one = "1";
        assertThat(Utilities.leftPadding(one, 4, ' '), is("   1"));

        String two = "22";
        assertThat(Utilities.leftPadding(two, 2, ' '), is("22"));

        String zero = "";
        assertThat(Utilities.leftPadding(zero, 8, '*'), is("********"));


    }
}