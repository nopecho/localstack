package com.nopecho.localstack.message;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component("sns")
@RequiredArgsConstructor
public class SNSMessageSender implements MessageSender{

    private final ObjectMapper mapper;
    private final AmazonSNS sns;
    @Value("${cloud.aws.sns.any-sns.topic-arn}")
    private String topic;

    @Override
    public void send(Object o) throws JsonProcessingException {
        PublishRequest request = new PublishRequest(topic, mapper.writeValueAsString(o));
        sns.publish(request);
        log.info("SNS Message Publish : {}", mapper.writeValueAsString(o));
    }
}