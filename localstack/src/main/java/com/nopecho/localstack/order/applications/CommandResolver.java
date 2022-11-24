package com.nopecho.localstack.order.applications;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nopecho.localstack.order.command.Command;
import com.nopecho.localstack.message.MessageSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CommandResolver {
    private final Dispatcher dispatcher;
    private final MessageSender sqsSender;
    private final MessageSender snsSender;

    public CommandResolver(Dispatcher dispatcher,
                           @Qualifier("sqs") MessageSender sqsSender,
                           @Qualifier("sns") MessageSender snsSender) {
        this.dispatcher = dispatcher;
        this.sqsSender = sqsSender;
        this.snsSender = snsSender;
    }


    public void resolveBySqs(Command command) throws JsonProcessingException {
        dispatcher.doDispatch(command, sqsSender);
    }

    public void resolveBySns(Command command) throws JsonProcessingException {
        dispatcher.doDispatch(command, snsSender);
    }
}