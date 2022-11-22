package com.nopecho.localstack.order.command;

import lombok.Getter;
import lombok.Setter;

public interface Command {

    @Getter
    @Setter
    class Create {
        private Long id;
        private String product;
        private String payment;
        private Integer price;
    }

    @Getter
    @Setter
    class Update {
        private String product;
        private String payment;
        private Integer price;
    }
}