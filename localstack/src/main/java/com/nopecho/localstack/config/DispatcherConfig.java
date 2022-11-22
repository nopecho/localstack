package com.nopecho.localstack.config;

import com.nopecho.localstack.order.command.CreateCommandHandler;
import com.nopecho.localstack.order.command.DeleteCommandHandler;
import com.nopecho.localstack.order.command.UpdateCommandHandler;
import com.nopecho.localstack.order.service.Dispatcher;
import com.nopecho.localstack.order.service.OrderDispatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DispatcherConfig {

    @Bean
    public Dispatcher dispatcher(){
        return new OrderDispatcher(
                new CreateCommandHandler(),
                new UpdateCommandHandler(),
                new DeleteCommandHandler()
        );
    }
}