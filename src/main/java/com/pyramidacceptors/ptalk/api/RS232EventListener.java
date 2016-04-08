package com.pyramidacceptors.ptalk.api;

import com.pyramidacceptors.ptalk.api.event.PTalkEvent;

/**
 * Created by cory on 4/7/2016.
 * <p/>
 * Internal event listener to prevent evnets from escaping to the client without
 * first being filtered by the acceptor instance.
 */
interface RS232EventListener {
    void changeEventReceived(PTalkEvent e);
}
