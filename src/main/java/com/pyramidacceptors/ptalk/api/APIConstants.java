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

/** 
 * Provides constant strings and enumerations used for API transactions<br>
 * between client code and the {@code ICommDevice}
 * <br>
 * @author <a href="mailto:cory@pyramidacceptors.com">Cory Todd</a>
 * @since 1.0.0.0
 */
public abstract class APIConstants {
    
    /**
     * String response for client code
     */
    private static final String API_REVISION = "1.2.4";
    
    /**
     * Global, default timeout unless otherwise specified
     */
    public static final int COMM_TIMEOUT = 400;
}
