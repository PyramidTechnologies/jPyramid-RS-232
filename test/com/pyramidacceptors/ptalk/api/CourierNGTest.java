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
public class CourierNGTest {
        
    public CourierNGTest() {
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
     * Test of getCommsOkay method, of class Courier.
     */
    @Test
    public void testGetCommsOkay() {
        System.out.println("getCommsOkay");
        Courier instance = null;
        boolean expResult = false;
        boolean result = instance.getCommsOkay();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addChangeListener method, of class Courier.
     */
    @Test
    public void testAddChangeListener() {
        System.out.println("addChangeListener");
        PTalkEventListener l = null;
        Courier instance = null;
        instance.addChangeListener(l);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeChangeListeners method, of class Courier.
     */
    @Test
    public void testRemoveChangeListeners() {
        System.out.println("removeChangeListeners");
        Courier instance = null;
        instance.removeChangeListeners();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeChangeListener method, of class Courier.
     */
    @Test
    public void testRemoveChangeListener() {
        System.out.println("removeChangeListener");
        PTalkEventListener l = null;
        Courier instance = null;
        instance.removeChangeListener(l);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of stopThread method, of class Courier.
     */
    @Test
    public void testStopThread() {
        System.out.println("stopThread");
        Courier instance = null;
        instance.stopThread();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of run method, of class Courier.
     */
    @Test
    public void testRun() {
        System.out.println("run");
        Courier instance = null;
        instance.run();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
