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

package com.pyramidacceptors.ptalk.api.event;

import com.pyramidacceptors.ptalk.api.APIConstants;
import java.util.EnumSet;
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
public class PTalkEventNGTest {
    
    public PTalkEventNGTest() {
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
     * Test of getEventId method, of class PTalkEvent.
     */
    @Test
    public void testGetEventId() {
        System.out.println("getEventId");
        PTalkEvent instance = null;
        EnumSet expResult = null;
        EnumSet result = instance.getEventId();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBillName method, of class PTalkEvent.
     */
    @Test
    public void testGetBillName() {
        System.out.println("getBillName");
        PTalkEvent instance = null;
        APIConstants.BillNames expResult = null;
        APIConstants.BillNames result = instance.getBillName();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFriendlyString method, of class PTalkEvent.
     */
    @Test
    public void testGetFriendlyString() {
        System.out.println("getFriendlyString");
        PTalkEvent instance = null;
        String expResult = "";
        String result = instance.getFriendlyString();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
