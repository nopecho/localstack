package com.nopecho.localstack.order.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nopecho.localstack.order.command.Command;
import com.nopecho.localstack.order.command.CreateCommand;
import com.nopecho.localstack.order.command.DeleteCommand;
import com.nopecho.localstack.order.command.UpdateCommand;
import com.nopecho.localstack.order.service.OrderResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class CommandController {

    private final OrderResolver resolver;

    @PostMapping
    public String create(@RequestBody Command.Create cmd) throws JsonProcessingException {
        Command command = CreateCommand.of(
                cmd.getId(),
                cmd.getProduct(),
                cmd.getPayment(),
                cmd.getPrice()
        );
        resolver.resolve(command);
        return "ok";
    }

    @PatchMapping("/{orderId}")
    public String update(@PathVariable Long orderId,
                         @RequestBody Command.Update cmd) throws JsonProcessingException {
        Command command = UpdateCommand.of(
                orderId,
                cmd.getProduct(),
                cmd.getPayment(),
                cmd.getPrice()
        );
        resolver.resolve(command);
        return "ok";
    }

    @DeleteMapping("/{orderId}")
    public String delete(@PathVariable Long orderId) throws JsonProcessingException {
        Command command = DeleteCommand.of(orderId);
        resolver.resolve(command);
        return "ok";
    }
}