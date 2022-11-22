package com.nopecho.localstack.order.command;

import lombok.Value;

@Value(staticConstructor = "of")
public class UpdateCommand implements Command{
    Long orderId;
    String product;
    String payment;
    Integer price;
}
