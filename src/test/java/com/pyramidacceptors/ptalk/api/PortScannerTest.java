package com.pyramidacceptors.ptalk.api;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Please install com0com and <a href="https://github.com/corytodd/soft-bill">Soft-Bill</a>
 * to run this test.
 * Created by catix on 5/17/2015.
 */
public class PortScannerTest {

    private String testPort = "";

    /**
     * Initialize the mock monitor used for event testing
     */
    @Before
    public void setup() {

        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = PortScannerTest.class.getClassLoader().getResource("test.properties").openStream();
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
     * Test assumes you're slave is on port test.properties.test_port
     * @throws Exception
     */
    @Test
    public void testFind() throws Exception {
        if(testPort.length() > 0) {
            String port = PortScanner.find();
            if (port.length() > 0)
                assertThat(port, is(testPort));
        }

    }
}