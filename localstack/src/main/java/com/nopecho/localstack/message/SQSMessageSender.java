package com.nopecho.localstack.message;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component("sqs")
@RequiredArgsConstructor
public class SQSMessageSender implements MessageSender{

    private final ObjectMapper mapper;
    private final AmazonSQS sqs;
    @Value("${cloud.aws.sqs.any-sqs.url}")
    private String url;

    @Override
    public void send(Object o) throws JsonProcessingException {
        SendMessageRequest request = new SendMessageRequest(url, mapper.writeValueAsString(o));
        sqs.sendMessage(request);
        log.info("SQS Message Publish : {}", mapper.writeValueAsString(o));
    }
}
