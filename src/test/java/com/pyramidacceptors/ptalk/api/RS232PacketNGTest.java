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
public class RS232PacketNGTest {
    
    public RS232PacketNGTest() {
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
     * Test of isValid method, of class RS232Packet.
     */
    @Test
    public void testIsValid() {
        System.out.println("isValid");
        RS232Packet instance = new RS232Packet();
        boolean expResult = false;
        boolean result = instance.isValid();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getByteString method, of class RS232Packet.
     */
    @Test
    public void testGetByteString() {
        System.out.println("getByteString");
        RS232Packet instance = new RS232Packet();
        String expResult = "";
        String result = instance.getByteString();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toBytes method, of class RS232Packet.
     */
    @Test
    public void testToBytes() {
        System.out.println("toBytes");
        RS232Packet instance = new RS232Packet();
        byte[] expResult = null;
        byte[] result = instance.toBytes();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of size method, of class RS232Packet.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        RS232Packet instance = new RS232Packet();
        int expResult = 0;
        int result = instance.size();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get method, of class RS232Packet.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        int index = 0;
        RS232Packet instance = new RS232Packet();
        byte expResult = 0;
        byte result = instance.get(index);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pack method, of class RS232Packet.
     */
    @Test
    public void testPack() {
        System.out.println("pack");
        RS232Packet instance = new RS232Packet();
        IPacket expResult = null;
        IPacket result = instance.pack();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of replace method, of class RS232Packet.
     */
    @Test
    public void testReplace() {
        System.out.println("replace");
        int index = 0;
        byte b = 0;
        RS232Packet instance = new RS232Packet();
        boolean expResult = false;
        boolean result = instance.replace(index, b);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of or method, of class RS232Packet.
     */
    @Test
    public void testOr() {
        System.out.println("or");
        int index = 0;
        byte b = 0;
        RS232Packet instance = new RS232Packet();
        boolean expResult = false;
        boolean result = instance.or(index, b);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parseAsNew method, of class RS232Packet.
     */
    @Test
    public void testParseAsNew() {
        System.out.println("parseAsNew");
        byte[] bytes = null;
        RS232Packet instance = new RS232Packet();
        IPacket expResult = null;
        IPacket result = instance.parseAsNew(bytes);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInterrpretedEvents method, of class RS232Packet.
     */
    @Test
    public void testGetInterrpretedEvents() {
        System.out.println("getInterrpretedEvents");
        RS232Packet instance = new RS232Packet();
        EnumSet expResult = null;
        EnumSet result = instance.getInterrpretedEvents();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMessage method, of class RS232Packet.
     */
    @Test
    public void testGetMessage() {
        System.out.println("getMessage");
        RS232Packet instance = new RS232Packet();
        String expResult = "";
        String result = instance.getMessage();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class RS232Packet.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        RS232Packet instance = new RS232Packet();
        String expResult = "";
        String result = instance.toString();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class RS232Packet.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        RS232Packet instance = new RS232Packet();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class RS232Packet.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        RS232Packet instance = new RS232Packet();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCreditAction method, of class RS232Packet.
     */
    @Test
    public void testGetCreditAction() {
        System.out.println("getCreditAction");
        RS232Packet instance = new RS232Packet();
        RS232Socket.CreditActions expResult = null;
        RS232Socket.CreditActions result = instance.getCreditAction();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBillName method, of class RS232Packet.
     */
    @Test
    public void testGetBillName() {
        System.out.println("getBillName");
        RS232Packet instance = new RS232Packet();
        APIConstants.BillNames expResult = null;
        APIConstants.BillNames result = instance.getBillName();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
