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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Pyramid Technologies, Inc. 
 * Product: Pyramid API
 * Date: 14-07-2014 
 */
class PortScanner {
    private static final Logger logger = LoggerFactory.getLogger(PortScanner.class);

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
        RS232Socket socket = new RS232Socket();

        // Get all currently available ports
        String[] availablePorts = PyramidPort.getPortList();

        for (String p : availablePorts) {

            try {
                port = new PyramidPort.PortBuilder(p).build();
                RS232Configuration.INSTANCE.setPollrate(100);
                courier = new Courier(port, socket);
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
                logger.error("Unable to open port: {} -> {}", p, ex);
            }

            // Stop when we find a port
            if (!portName.equals("")) {
                break;
            }
        }

        return portName;
    }
}
