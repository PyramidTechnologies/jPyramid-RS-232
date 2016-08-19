package com.pyramidacceptors.ptalk.api.event;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by cory on 4/8/2016.
 */
public class PTalkEventTest {

    @Test
    public void testGetEventId() throws Exception {

        // Generate some general events and make sure their ID is properly set
        PTalkEvent e;

        for(Events id : Events.values()) {
            e = new PTalkEvent(this, id, "Test");
            assertThat(e.getEventId().contains(id), is(true));
        }

    }

    @Test
    public void testGetId() throws Exception {
        // Generate some general events and make sure their ID is properly set
        PTalkEvent e;

        for(Events id : Events.values()) {
            e = new PTalkEvent(this, id, "Test");
            assertThat(e.getId(), is(id));
        }
    }

    @Test
    public void testGetPacketString() throws Exception {
        // Generate some general events and make sure their ID is properly set
        PTalkEvent e;

        for(Events id : Events.values()) {
            e = new PTalkEvent(this, id, "Test");
            assertThat(e.getPacketString(), is("Test"));
        }
    }
}