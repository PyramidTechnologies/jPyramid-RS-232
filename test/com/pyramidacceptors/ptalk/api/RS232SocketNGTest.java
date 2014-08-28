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
public class RS232SocketNGTest {
    
    public RS232SocketNGTest() {
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
     * Test of generateCommand method, of class RS232Socket.
     */
    @Test
    public void testGenerateCommand() {
        System.out.println("generateCommand");
        RS232Socket instance = new RS232Socket();
        byte[] expResult = null;
        byte[] result = instance.generateCommand();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parseResponse method, of class RS232Socket.
     */
    @Test
    public void testParseResponse() {
        System.out.println("parseResponse");
        byte[] bytes = null;
        RS232Socket instance = new RS232Socket();
        PTalkEvent expResult = null;
        PTalkEvent result = instance.parseResponse(bytes);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMaxPacketRespSize method, of class RS232Socket.
     */
    @Test
    public void testGetMaxPacketRespSize() {
        System.out.println("getMaxPacketRespSize");
        RS232Socket instance = new RS232Socket();
        int expResult = 0;
        int result = instance.getMaxPacketRespSize();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
