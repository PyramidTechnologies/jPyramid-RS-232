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

import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author catix
 */
public class PortScannerNGTest {
    
    public PortScannerNGTest() {
    }

    /**
     * Test of find method, of class PortScanner. On systems with no devices<br>
     * attached this should pass.
     */
    @Test
    public void testFindSholdPassWhenNoDevicesAttached() {
        System.out.println("find");
        String expResult = "";
        String result = PortScanner.find();
        assertEquals(result, expResult);
    }
    
    /**
     * Test of find method, of class PortScanner. On systems with devices<br>
     * attached this should pass.
     */
    @Test
    public void testFindSholdPassWhenDeviceConnected() {
        System.out.println("find - COM4");
        String expResult = "COM4";
        String result = PortScanner.find();
        assertEquals(result, expResult);
    }    
    
}
