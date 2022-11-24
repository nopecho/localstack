package com.nopecho.localstack.message;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SQSMessageListener implements MessageListener<String> {

    @SqsListener(value = "${cloud.aws.sqs.any-sqs.name}")
    public void receive(String msg) {
        log.info("SQS Message Received 1 : {}", msg);
    }

    @SqsListener(value = "${cloud.aws.sqs.any-sqs-2.name}")
    public void receive2(String msg) {
        log.info("SQS Message Received 2 : {}", msg);
    }
}