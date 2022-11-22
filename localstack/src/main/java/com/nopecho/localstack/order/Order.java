package com.nopecho.localstack.order;

import lombok.Getter;

@Getter
public class Order {
    private final Long id;
    private final String product;
    private final String payment;
    private final Integer price;

    public Order(Long id, String product, String payment, Integer price) {
        this.id = id;
        this.product = product;
        this.payment = payment;
        this.price = price;
    }
}