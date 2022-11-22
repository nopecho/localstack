package com.nopecho.localstack.order.command;

import lombok.Value;

@Value(staticConstructor = "of")
public class DeleteCommand implements Command{
    Long orderId;
}
