package com.nopecho.localstack.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nopecho.localstack.order.command.Command;
import com.nopecho.localstack.order.command.CommandHandler;
import com.nopecho.localstack.message.MessageSender;

import java.util.ArrayList;
import java.util.List;

public class OrderDispatcher implements Dispatcher {

    private final List<CommandHandler> handlers = new ArrayList<>();

    public OrderDispatcher(CommandHandler... handlers) {
        this.handlers.addAll(List.of(handlers));
    }

    @Override
    public void doDispatch(Command command, MessageSender sender) throws JsonProcessingException {
        CommandHandler handler = handlers.stream()
                .filter(h -> h.isSupport(command))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Not found handlers"));
        handler.setSender(sender);
        handler.handle(command);
    }
}