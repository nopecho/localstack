package com.nopecho.localstack.message;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface MessageSender<R, T> {
    R send(T t) throws JsonProcessingException;
}
