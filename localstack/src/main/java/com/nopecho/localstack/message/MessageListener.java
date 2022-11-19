package com.nopecho.localstack.message;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface MessageListener<T> {
    void receive(T t) throws JsonProcessingException;
}
