package com.nana.payments.transaction.publisher;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.nana.payments.transaction.config.RabbitMQConfig;
import com.nana.payments.transaction.event.TransactionCreatedEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TransactionEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void publish(TransactionCreatedEvent event) {

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.TRANSACTION_CREATED_QUEUE,
                event);
    }
}