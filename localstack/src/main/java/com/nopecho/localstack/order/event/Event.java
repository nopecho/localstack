package com.nopecho.localstack.order.event;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Event {
    private final Long id;
    private final String type;
    private final LocalDateTime occurredAt;
    private final Object payload;
}