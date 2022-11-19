package com.nopecho.localstack.message;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SQSMessageSender implements MessageSender<SendMessageResult, Message.Sample>{

    private final ObjectMapper mapper;
    private final AmazonSQS sqs;
    @Value("${cloud.aws.sqs.sqs.url}")
    private String url;

    @Override
    public SendMessageResult send(Message.Sample msg) throws JsonProcessingException {
        SendMessageRequest request = new SendMessageRequest(url, mapper.writeValueAsString(msg));
        return sqs.sendMessage(request);
    }
}
