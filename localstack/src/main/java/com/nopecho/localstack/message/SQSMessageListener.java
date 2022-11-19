package com.nopecho.localstack.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SQSMessageListener implements MessageListener<String> {

    private final ObjectMapper mapper;

    @SqsListener(value = "${cloud.aws.sqs.sqs.name}")
    public void receive(String msg) throws JsonProcessingException {
        log.info("SQS Message Received : {}", msg);

        Message.Sample object = mapper.readValue(msg, Message.Sample.class);
        log.info("Message to Object : {}", object.getMessage());
    }
}
