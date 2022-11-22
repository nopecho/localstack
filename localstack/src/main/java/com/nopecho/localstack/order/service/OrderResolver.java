package com.nopecho.localstack.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nopecho.localstack.order.command.Command;
import com.nopecho.localstack.message.MessageSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderResolver {
    private final Dispatcher dispatcher;
    private final MessageSender sender;

    public void resolve(Command command) throws JsonProcessingException {
        dispatcher.doDispatch(command, sender);
    }
}