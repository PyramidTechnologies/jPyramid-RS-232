package com.pyramidacceptors.ptalk.api.event;

import java.util.EnumSet;
import java.util.EventObject;

/**
 * Created by cory on 4/7/2016.
 */
public class PTalkEvent extends EventObject {
    private static final long serialVersionUID = 1L;

    private final String packetString;
    private final Events eventId;
    private final EnumSet<Events> compatiblitySet;

    /**
     * Generates a new PTalk event.
     * @param source Object that created this event
     * @param id Events id
     * @param rawMessage Stringified packet
     */
    public PTalkEvent(Object source, Events id, String rawMessage) {
        super(source);

        this.eventId = id;
        this.packetString = rawMessage;

        this.compatiblitySet = EnumSet.of(id);
    }

    /**
     * Provides a set of events that generated this event
     *
     * @deprecated Use getId() instead
     * @return EnumSet
     */
    public EnumSet<Events> getEventId() { return this.compatiblitySet.clone(); }

    /**
     * Returns the event id for this event.
     * @return Events
     */
    public Events getId() { return this.eventId; }

    /**
     * Returns the stringified packet that this event was decoded from
     * @return String
     */
    public String getPacketString() { return this.packetString; }
}
