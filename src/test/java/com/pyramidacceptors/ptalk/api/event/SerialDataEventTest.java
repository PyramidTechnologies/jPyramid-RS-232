package com.pyramidacceptors.ptalk.api.event;

import com.pyramidacceptors.ptalk.api.MessageType;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by cory on 4/8/2016.
 */
public class SerialDataEventTest {

    @Test
    public void testGetMessageType() throws Exception {

        SerialDataEvent event = new SerialDataEvent(this, MessageType.Master, "master test");
        assertThat(event.getMessageType(), is(MessageType.Master));

        event = new SerialDataEvent(this, MessageType.Slave, "slave test");
        assertThat(event.getMessageType(), is(MessageType.Slave));
    }

    @Test
    public void testNewTxEvent() throws Exception {
        SerialDataEvent event = SerialDataEvent.newTxEvent(this,  "static master test");
        assertThat(event.getMessageType(), is(MessageType.Master));
    }

    @Test
    public void testNewRxEvent() throws Exception {
        SerialDataEvent event = SerialDataEvent.newRxEvent(this,  "static slave test");
        assertThat(event.getMessageType(), is(MessageType.Slave));
    }
}