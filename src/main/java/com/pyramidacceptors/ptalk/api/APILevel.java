package com.pyramidacceptors.ptalk.api;

/**
* API Level assists in detecting how to talk to the target
* {@code ICommDevice}
* <br>
* @author <a href="mailto:cory@pyramidacceptors.com">Cory Todd</a>
* @since 1.0.0.0
*/
public enum APILevel {
   /**
    * V1 devices are older and support standard RS-232 commands, nothing more
    */
   V1,
   /**
    * V2 devices support extended features TODO. V1 is a subset of V2
    */
   V2
}
