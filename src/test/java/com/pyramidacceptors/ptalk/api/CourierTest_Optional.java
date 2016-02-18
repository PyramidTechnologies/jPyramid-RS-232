package com.pyramidacceptors.ptalk.api;

import com.pyramidacceptors.ptalk.api.event.PTalkEvent;
import com.pyramidacceptors.ptalk.api.event.PTalkEventListener;
import org.junit.Test;

import org.junit.Before;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.verify;

/**
 * Test assumes you're slave is on port test.properties.test_port
 * Created by catix on 5/17/2015.
 */
public class CourierTest_Optional {


    @Mock
    EventMonitor monitor;

    private String testPort = "";

    /**
     * Initialize the mock monitor used for event testing
     */
    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);

        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = CourierTest_Optional.class.getClassLoader().getResource("test.properties").openStream();
            prop.load(input);

            testPort = prop.getProperty("test_port");

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if(testPort.length() == 0) {
            Logger.getAnonymousLogger().log(Level.ALL, "COM port not set in test.properties. Skipping test " +
            this.getClass().getCanonicalName());
        }

    }

    /**
     * This tests all of the function since they are so interconnected. You have
     * to register a listener to detect events and to verify that coomms are okay.
     * You also have to be running in order to simulate thread stops.
     * @throws Exception
     */
    @Test
    public void testRun() throws Exception {
        if(testPort.length() > 0) {
            PyramidPort port = new PyramidPort.PortBuilder(testPort).build();

            RS232Configuration.INSTANCE.setPollrate(50);
            Courier c = new Courier(port, new RS232Socket());


            // Not yet running, comms should be okay
            assertThat(true, is(c.getCommsOkay()));

            c.addChangeListener(monitor);

            // Run async
            c.start();

            ArgumentCaptor<PTalkEvent> captor = ArgumentCaptor.forClass(PTalkEvent.class);

            // This will verify that the event was fired within our monitor
            // Note, this is a virtual test and actually just checks the call stack so
            // don't expect anything to actually execute within "eventReceived".
            // There should be exactly one events for each of the Events in eventSet.
            verify(monitor).changeEventReceived(captor.capture());

            // Still running, comms should be okay
            assertThat(true, is(c.getCommsOkay()));

            EventMonitor monitorTwo = new EventMonitor();
            c.addChangeListener(monitorTwo);

            // TODO verify that these are detached
            c.removeChangeListener(monitor);
            c.removeChangeListeners();

            // Clean up and shutdown.
            c.stopThread();
            port.closePort();


        }

    }

    class EventMonitor implements PTalkEventListener {
        @Override
        public void changeEventReceived(PTalkEvent evt) {

        }
    }
}