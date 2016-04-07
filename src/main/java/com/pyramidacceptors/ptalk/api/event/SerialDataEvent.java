package com.pyramidacceptors.ptalk.api.event;

import com.pyramidacceptors.ptalk.api.MessageType;

/**
 * Created by cory on 4/7/2016.
 */
public class SerialDataEvent extends PTalkEvent {
    public final MessageType messageType;
    public final String message;


    /**
     * Creates a new event
     *
     * @param source     origin the event
     * @param rawMessage Stringified packet that generated this event
     */
    public SerialDataEvent(Object source, MessageType messageType, String rawMessage) {
        super(source, Events.SerialData, rawMessage);

        this.messageType = messageType;
        this.message = rawMessage;
    }

    public MessageType getMessageType() {
        return this.messageType;
    }

    public static SerialDataEvent newTxEvent(Object source, String message) {
        return new SerialDataEvent(source, MessageType.Master, message);
    }

    public static SerialDataEvent newRxEvent(Object source, String message) {
        return new SerialDataEvent(source, MessageType.Slave, message);
    }
}
