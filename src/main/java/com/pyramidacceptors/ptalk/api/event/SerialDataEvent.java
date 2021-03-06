package com.pyramidacceptors.ptalk.api.event;

import com.pyramidacceptors.ptalk.api.MessageType;

/**
 * Created by cory on 4/7/2016.
 * <br>
 * This event encapsulates a packet being sent by this host (master)
 * or send my the target acceptor (slave)
 *
 * @since 1.2.5
 */
public class SerialDataEvent extends PTalkEvent {
    public final MessageType messageType;
    public final String message;


    /**
     * Creates a new serial data event. This represents and entire master or slave
     * message as it has been been routed through the message loop.
     *
     * @param source     origin the event
     * @param messageType Message direction
     * @param rawMessage Stringified packet that generated this event
     */
    public SerialDataEvent(Object source, MessageType messageType, String rawMessage) {
        super(source, Events.SerialData, rawMessage);

        this.messageType = messageType;
        this.message = rawMessage;
    }

    /**
     * Generates a new master, or Tx, type serial data event
     *
     * @param source  Object that raised this event
     * @param message Stringified packet
     * @return SerialDataEvent
     */
    public static SerialDataEvent newTxEvent(Object source, String message) {
        return new SerialDataEvent(source, MessageType.Master, message);
    }

    /**
     * Generates a new slave, or Rx, type serial data event
     *
     * @param source  Object that raised this event
     * @param message Stringified packet
     * @return SerialDataEvent event containing packet information
     */
    public static SerialDataEvent newRxEvent(Object source, String message) {
        return new SerialDataEvent(source, MessageType.Slave, message);
    }

    /**
     * Returns the MessageType for this event
     *
     * @return MessageType
     */
    public MessageType getMessageType() {
        return this.messageType;
    }
}
