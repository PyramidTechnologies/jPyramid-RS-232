package com.pyramidacceptors.ptalk.api;

import com.pyramidacceptors.ptalk.api.event.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
/**
 * Created by catix on 5/17/2015.
 */
public class PyramidAcceptorTest {

    @Mock
    EventMonitor monitor;

    /**
     * Initialize the mock monitor used for event testing
     */
    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testPollRate() throws Exception {
        PyramidAcceptor acceptor = PyramidAcceptor.asTest();
        acceptor.setPollRate(500);
        assertThat(500, is(acceptor.getPollRate()));

        // Too low
        assertThat(true, is(not(acceptor.setPollRate(-1))));

        // Too high
        assertThat(true, is(not(acceptor.setPollRate(Integer.MAX_VALUE))));
    }

    @Test
    public void testChangeEventReceived() throws Exception {
        PyramidAcceptor acceptor = PyramidAcceptor.asTest();
        acceptor.addChangeListener(monitor);

        // Generate some events
        List<PTalkEvent> eventTestSet = new ArrayList<>();
        PTalkEvent p;

        RS232Configuration.INSTANCE.setEventMask(RS232Configuration.ALL_EVENTS_MASK);
        for (Events e: EnumSet.allOf(Events.class)) {
            p = new PTalkEvent(this, e, "");
            eventTestSet.add(p);
            acceptor.changeEventReceived(p);
        }

        // Setup our event capture test
        ArgumentCaptor<PTalkEvent> captor = ArgumentCaptor.forClass(PTalkEvent.class);

        // This will verify that the event was fired within our monitor
        // Note, this is a virtual test and actually just checks the call stack so
        // don't expect anything to actually execute within "eventReceived".
        // There should be exactly one events for each of the Events in eventSet.
        verify(monitor, times(eventTestSet.size())).changeEventReceived(captor.capture());

        captor.getAllValues();


        acceptor.removeChangeListener(monitor);
    }



    class EventMonitor implements PTalkEventListener {
        @Override
        public void changeEventReceived(PTalkEvent evt) {

        }
    }
}