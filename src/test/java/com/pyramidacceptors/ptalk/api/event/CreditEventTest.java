package com.pyramidacceptors.ptalk.api.event;

import com.pyramidacceptors.ptalk.api.BillNames;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by cory on 4/8/2016.
 */
public class CreditEventTest {

    /**
     * Ensure that the constructor is correct and the the bill name is returning properly
     * @throws Exception
     */
    @Test
    public void testGetBillName() throws Exception {

        CreditEvent event = new CreditEvent(this, "Testing", BillNames.Bill1);

        assertThat(event.getBillName(), is(BillNames.Bill1));

        assertThat(event.getId(), is(Events.Credit));
    }
}