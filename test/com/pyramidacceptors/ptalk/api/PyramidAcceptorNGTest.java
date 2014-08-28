/*
 * Copyright (C) 2014 catix
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.pyramidacceptors.ptalk.api;

import com.pyramidacceptors.ptalk.api.event.PTalkEvent;
import com.pyramidacceptors.ptalk.api.event.PTalkEventListener;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author catix
 */
public class PyramidAcceptorNGTest {
    
    public PyramidAcceptorNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of valueOfRS232 method, of class PyramidAcceptor.
     */
    @Test
    public void testValueOfRS232_String() throws Exception {
        System.out.println("valueOfRS232");
        String portName = "";
        PyramidAcceptor expResult = null;
        PyramidAcceptor result = PyramidAcceptor.valueOfRS232(portName);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of valueOfRS232 method, of class PyramidAcceptor.
     */
    @Test
    public void testValueOfRS232_6args() throws Exception {
        System.out.println("valueOfRS232");
        IConfiguration config = null;
        String portName = "";
        int baudRate = 0;
        int databits = 0;
        int stopbits = 0;
        int parity = 0;
        PyramidAcceptor expResult = null;
        PyramidAcceptor result = PyramidAcceptor.valueOfRS232(config, portName, baudRate, databits, stopbits, parity);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of valueOfRS232 method, of class PyramidAcceptor.
     */
    @Test
    public void testValueOfRS232_0args() throws Exception {
        System.out.println("valueOfRS232");
        PyramidAcceptor expResult = null;
        PyramidAcceptor result = PyramidAcceptor.valueOfRS232();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addChangeListener method, of class PyramidAcceptor.
     */
    @Test
    public void testAddChangeListener() {
        System.out.println("addChangeListener");
        PTalkEventListener l = null;
        PyramidAcceptor instance = null;
        instance.addChangeListener(l);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeChangeListener method, of class PyramidAcceptor.
     */
    @Test
    public void testRemoveChangeListener() {
        System.out.println("removeChangeListener");
        PTalkEventListener l = null;
        PyramidAcceptor instance = null;
        instance.removeChangeListener(l);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of connect method, of class PyramidAcceptor.
     */
    @Test
    public void testConnect() {
        System.out.println("connect");
        PyramidAcceptor instance = null;
        instance.connect();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of disconnect method, of class PyramidAcceptor.
     */
    @Test
    public void testDisconnect() {
        System.out.println("disconnect");
        PyramidAcceptor instance = null;
        instance.disconnect();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPollRate method, of class PyramidAcceptor.
     */
    @Test
    public void testGetPollRate() {
        System.out.println("getPollRate");
        PyramidAcceptor instance = null;
        int expResult = 0;
        int result = instance.getPollRate();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPollRate method, of class PyramidAcceptor.
     */
    @Test
    public void testSetPollRate() {
        System.out.println("setPollRate");
        int rate = 0;
        PyramidAcceptor instance = null;
        boolean expResult = false;
        boolean result = instance.setPollRate(rate);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isConnected method, of class PyramidAcceptor.
     */
    @Test
    public void testIsConnected() {
        System.out.println("isConnected");
        PyramidAcceptor instance = null;
        boolean expResult = false;
        boolean result = instance.isConnected();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPortName method, of class PyramidAcceptor.
     */
    @Test
    public void testGetPortName() {
        System.out.println("getPortName");
        PyramidAcceptor instance = null;
        String expResult = "";
        String result = instance.getPortName();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changeEventReceived method, of class PyramidAcceptor.
     */
    @Test
    public void testChangeEventReceived() {
        System.out.println("changeEventReceived");
        PTalkEvent evt = null;
        PyramidAcceptor instance = null;
        instance.changeEventReceived(evt);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
