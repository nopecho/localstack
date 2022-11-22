package com.nopecho.localstack.message;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface MessageSender {
    void send(Object o) throws JsonProcessingException;
}
