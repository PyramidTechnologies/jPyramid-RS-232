package com.pyramidacceptors.ptalk.api;

import com.pyramidacceptors.ptalk.api.event.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.io.InputStream;
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

        // Add all known events to the set and pass that as our mocked event
        EnumSet<Events> eventSet = EnumSet.allOf(Events.class);
        PTalkEvent event = new PTalkEvent(this,BillNames.Bill1, "", eventSet);

        // Generate some events
        acceptor.changeEventReceived(event);

        // Setup our event capture test
        ArgumentCaptor<PTalkEvent> captor = ArgumentCaptor.forClass(PTalkEvent.class);

        // This will verify that the event was fired within our monitor
        // Note, this is a virtual test and actually just checks the call stack so
        // don't expect anything to actually execute within "eventReceived".
        // There should be exactly one events for each of the Events in eventSet.
        verify(monitor, times(eventSet.size())).changeEventReceived(captor.capture());

        List<PTalkEvent> capturedEvents = captor.getAllValues();

        PTalkEvent pureEvent;
        new AcceptingEvent(this, event.getBillName(), event.getFriendlyString(), event.getEventId());
        new BillJammedEvent(this, event.getBillName(), event.getFriendlyString(), event.getEventId());
        new BillRejectedEvent(this, event.getBillName(), event.getFriendlyString(), event.getEventId());
        new CasseteMissingEvent(this, event.getBillName(), event.getFriendlyString(), event.getEventId());
        new CheatedEvent(this, event.getBillName(), event.getFriendlyString(), event.getEventId());
        new CreditEvent(this, event.getBillName(), event.getFriendlyString(), event.getEventId());
        new EscrowedEvent(this, event.getBillName(), event.getFriendlyString(), event.getEventId());
        new FailureEvent(this, event.getBillName(), event.getFriendlyString(), event.getEventId());
        new IdlingEvent(this, event.getBillName(), event.getFriendlyString(), event.getEventId());
        new InvalidMessageEvent(this, event.getBillName(), event.getFriendlyString(), event.getEventId());
        new PowerUpEvent(this, event.getBillName(), event.getFriendlyString(), event.getEventId());
        new ReturnedEvent(this, event.getBillName(), event.getFriendlyString(), event.getEventId());
        new ReturningEvent(this, event.getBillName(), event.getFriendlyString(), event.getEventId());
        new StackingEvent(this, event.getBillName(), event.getFriendlyString(), event.getEventId());
        new StackedEvent(this, event.getBillName(), event.getFriendlyString(), event.getEventId());
        new StackerFullEvent(this, event.getBillName(), event.getFriendlyString(), event.getEventId());




        acceptor.removeChangeListener(monitor);
    }



    class EventMonitor implements PTalkEventListener {
        @Override
        public void changeEventReceived(PTalkEvent evt) {

        }
    }
}