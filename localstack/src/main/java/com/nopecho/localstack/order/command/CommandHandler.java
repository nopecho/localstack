package com.nopecho.localstack.order.command;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nopecho.localstack.message.MessageSender;

public interface CommandHandler {
    boolean isSupport(Command command);
    void setSender(MessageSender messageSender);
    void handle(Command command) throws JsonProcessingException;
}