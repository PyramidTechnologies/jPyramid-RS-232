/*
 * Copyright (C) 2014 Pyramid Technologies, Inc.
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

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Pyramid Technologies, Inc. 
 * Product: Pyramid API
 * Date: 14-07-2014 
 */
class PortScanner {

    /**
     * Attempts to auto detect a slave device connected to master. The slave
     * is polled for 1 second and if no errors are reported, we return the<br>
     * name of the port the slave is connected on.<br>
     *
     * @return
     */
    public static String find() {
        String portName = "";
        PyramidPort port;
        Courier courier;
        ISocket socket = new RS232Socket();

        // Get all currently available ports
        String[] availablePorts = PyramidPort.getPortList();

        for (String p : availablePorts) {

            try {
                port = new PyramidPort.PortBuilder(p).build();
                courier = new Courier(port, 100, socket);
                courier.start();
                
                // Wait for 10 polls before querying comm status
                try { Thread.sleep(1000); } 
                catch (InterruptedException ex) {}                                
                
                if (courier.getCommsOkay()) {
                    portName = p;
                }
                
                courier.stopThread();
                port.closePort();
                
            } catch (PyramidDeviceException ex) {
                Logger.getLogger(PortScanner.class.getName()).log(Level.INFO,
                        String.format("Unable to open port: %s", p));
            }

            // Stop when we find a port
            if (!portName.equals("")) {
                break;
            }
        }

        return portName;
    }

    private PortScanner() {
    }

}
