package com.pyramidacceptors.ptalk.api.event;

/**
 * Created by cory on 4/8/2016.
 * <p/>
 * Event is raised when the serial connection is suspected of being lost.
 *
 * @since 1.2.5
 */
public class ConnectionFailureEvent extends PTalkEvent {

    public static final String CONNECTION_FAILURE = "Serial Connection Failure";

    private final int failureCount;

    /**
     * Creates a new failure event tracking the total serial failures prior to this event.
     * A transmission failure occurs when a either IOException, timeout, or any other exceptional
     * condition prevents a serial port write request from completing. This may also indicate a
     * count of non-responses from the target device.
     *
     * @param source       Creator of this event
     * @param failureCount integer count of total tracked failures prior to this event. It is up to the
     *                     object that raises this event if this count is lifetime total or on a
     *                     per-connection basis.
     */
    public ConnectionFailureEvent(Object source, int failureCount) {
        super(source, Events.CommunicationFailure, CONNECTION_FAILURE);

        this.failureCount = failureCount;
    }

    /**
     * Returns the total number of failed transmissions before this event was raised.
     *
     * @return int
     */
    public int getFailureCount() {
        return this.failureCount;
    }
}
