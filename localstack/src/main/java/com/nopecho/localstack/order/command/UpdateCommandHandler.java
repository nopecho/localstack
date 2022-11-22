package com.nopecho.localstack.order.command;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nopecho.localstack.message.MessageSender;
import com.nopecho.localstack.order.event.Event;

import java.time.LocalDateTime;

public class UpdateCommandHandler implements CommandHandler{

    private MessageSender sender;

    @Override
    public boolean isSupport(Command command) {
        return command instanceof UpdateCommand;
    }

    @Override
    public void setSender(MessageSender sender) {
        this.sender = sender;
    }

    @Override
    public void handle(Command command) throws JsonProcessingException {
        Event event = Event.builder()
                .id(System.currentTimeMillis())
                .type("event.order.updated")
                .occurredAt(LocalDateTime.now())
                .payload(command)
                .build();
        sender.send(event);
    }
}