package com.nopecho.localstack.order.command;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nopecho.localstack.order.Order;
import com.nopecho.localstack.message.MessageSender;
import com.nopecho.localstack.order.event.Event;

import java.time.LocalDateTime;

public class CreateCommandHandler implements CommandHandler{

    private MessageSender sender;

    @Override
    public boolean isSupport(Command command) {
        return command instanceof CreateCommand;
    }

    @Override
    public void setSender(MessageSender sender){
        this.sender = sender;
    }

    @Override
    public void handle(Command command) throws JsonProcessingException {
        Order order = new Order(
                ((CreateCommand) command).getOrderId(),
                ((CreateCommand) command).getProduct(),
                ((CreateCommand) command).getPayment(),
                ((CreateCommand) command).getPrice()
        );

        Event event = Event.builder()
                .id(System.currentTimeMillis())
                .type("event.order.created")
                .occurredAt(LocalDateTime.now())
                .payload(order)
                .build();
        sender.send(event);
    }
}