package com.pyramidacceptors.ptalk.api;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Remote Data Tools
 * Created by cory on 5/18/2015.
 */
public class PyramidAcceptorTest_Optional {

    private String testPort = "";

    /**
     * Initialize the mock monitor used for event testing
     */
    @Before
    public void setup() {

        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = PyramidAcceptorTest_Optional.class.getClassLoader().getResource("test.properties").openStream();
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

    @Test
    public void testConnect() throws Exception {
/*
        if(testPort.length() > 0) {
            PyramidAcceptor acceptor = PyramidAcceptor.valueOfRS232(testPort);
            acceptor.connect();

            acceptor.disconnect();
        }
*/
    }
}
