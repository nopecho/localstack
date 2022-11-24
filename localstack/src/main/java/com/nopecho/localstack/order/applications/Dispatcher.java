package com.nopecho.localstack.order.applications;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nopecho.localstack.order.command.Command;
import com.nopecho.localstack.message.MessageSender;

public interface Dispatcher {
    void doDispatch(Command command, MessageSender sender) throws JsonProcessingException;
}
