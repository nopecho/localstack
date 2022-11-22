package com.nopecho.localstack.message;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SQSMessageSender implements MessageSender{

    private final ObjectMapper mapper;
    private final AmazonSQS sqs;
    @Value("${cloud.aws.sqs.sqs.url}")
    private String url;

    @Override
    public void send(Object o) throws JsonProcessingException {
        SendMessageRequest request = new SendMessageRequest(url, mapper.writeValueAsString(o));
        sqs.sendMessage(request);
    }
}
