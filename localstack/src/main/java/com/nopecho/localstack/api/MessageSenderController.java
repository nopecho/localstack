package com.nopecho.localstack.api;

import com.amazonaws.services.sqs.model.SendMessageResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.nopecho.localstack.message.Message;
import com.nopecho.localstack.message.MessageSender;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageSenderController {

    private final MessageSender<SendMessageResult, Message.Sample> messageSender;

    @PostMapping("/send")
    public SendMessageResult send(@RequestBody Message.Sample msg) throws JsonProcessingException {
        return messageSender.send(msg);
    }
}
