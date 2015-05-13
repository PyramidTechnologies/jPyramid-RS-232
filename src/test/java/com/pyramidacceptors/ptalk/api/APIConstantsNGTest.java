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
import static com.pyramidacceptors.ptalk.api.APIConstants.BillDirection.*;
import static com.pyramidacceptors.ptalk.api.APIConstants.BillNames.*;

import com.pyramidacceptors.ptalk.api.APIConstants.BillDirection;
import com.pyramidacceptors.ptalk.api.APIConstants.BillNames;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author catix
 */
public class APIConstantsNGTest {
    
    public APIConstantsNGTest() {
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

    @Test
    public void testBillNamesFromByte() {

        BillNames[] expected = {Invalid, Bill1, Bill2, Bill3, Bill4, 
            Bill5, Bill6, Bill7, Bill8, Bill9, Bill10, Bill11, Bill12 };
        
        BillNames result;
        for(byte b=0; b<13; b++) {                   
            result = BillNames.fromByte(b);
            assertSame(result, expected[b]);  
        }               
    }
    
    @Test
    public void testBillDirectionFromByte() {

        BillDirection[] expected = {LeftUp, RightUp, LeftDown, RightDown, Unset};
        
        BillDirection result;
        for(byte b=0; b<4; b++) {                   
            result = BillDirection.fromByte(b);
            assertSame(result, expected[b]);  
        }               
    }
    
}
