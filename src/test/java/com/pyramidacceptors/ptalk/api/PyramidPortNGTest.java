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

import java.util.regex.Pattern;
import jssc.SerialPortEventListener;
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
public class PyramidPortNGTest {
    
    public PyramidPortNGTest() {
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
     * Test of openPort method, of class PyramidPort.
     */
    @Test
    public void testOpenPort() throws Exception {
        System.out.println("openPort");
        PyramidPort instance = null;
        boolean expResult = false;
        boolean result = instance.openPort();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of closePort method, of class PyramidPort.
     */
    @Test
    public void testClosePort() throws Exception {
        System.out.println("closePort");
        PyramidPort instance = null;
        boolean expResult = false;
        boolean result = instance.closePort();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isOpen method, of class PyramidPort.
     */
    @Test
    public void testIsOpen() {
        System.out.println("isOpen");
        PyramidPort instance = null;
        boolean expResult = false;
        boolean result = instance.isOpen();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPortName method, of class PyramidPort.
     */
    @Test
    public void testGetPortName() {
        System.out.println("getPortName");
        PyramidPort instance = null;
        String expResult = "";
        String result = instance.getPortName();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readBytes method, of class PyramidPort.
     */
    @Test
    public void testReadBytes() throws Exception {
        System.out.println("readBytes");
        int count = 0;
        PyramidPort instance = null;
        byte[] expResult = null;
        byte[] result = instance.readBytes(count);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readHexString method, of class PyramidPort.
     */
    @Test
    public void testReadHexString() throws Exception {
        System.out.println("readHexString");
        int count = 0;
        String delimeter = "";
        PyramidPort instance = null;
        String expResult = "";
        String result = instance.readHexString(count, delimeter);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of write method, of class PyramidPort.
     */
    @Test
    public void testWrite() throws Exception {
        System.out.println("write");
        byte[] data = null;
        PyramidPort instance = null;
        instance.write(data);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addEventListener method, of class PyramidPort.
     */
    @Test
    public void testAddEventListener_SerialPortEventListener() throws Exception {
        System.out.println("addEventListener");
        SerialPortEventListener listener = null;
        PyramidPort instance = null;
        instance.addEventListener(listener);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addEventListener method, of class PyramidPort.
     */
    @Test
    public void testAddEventListener_SerialPortEventListener_int() throws Exception {
        System.out.println("addEventListener");
        SerialPortEventListener listener = null;
        int mask = 0;
        PyramidPort instance = null;
        instance.addEventListener(listener, mask);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPortList method, of class PyramidPort.
     */
    @Test
    public void testGetPortList_0args() {
        System.out.println("getPortList");
        String[] expResult = null;
        String[] result = PyramidPort.getPortList();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPortList method, of class PyramidPort.
     */
    @Test
    public void testGetPortList_String() {
        System.out.println("getPortList");
        String filter = "";
        String[] expResult = null;
        String[] result = PyramidPort.getPortList(filter);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPortList method, of class PyramidPort.
     */
    @Test
    public void testGetPortList_Pattern() {
        System.out.println("getPortList");
        Pattern pattern = null;
        String[] expResult = null;
        String[] result = PyramidPort.getPortList(pattern);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPortList method, of class PyramidPort.
     */
    @Test
    public void testGetPortList_Pattern_String() {
        System.out.println("getPortList");
        Pattern pattern = null;
        String filter = "";
        String[] expResult = null;
        String[] result = PyramidPort.getPortList(pattern, filter);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
