package com.nana.payments.notification.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.nana.payments.notification.event.TransactionCreatedEvent;

@Component
public class TransactionCreatedConsumer {

    @RabbitListener(queues = "transaction.created.queue")
    public void consume(TransactionCreatedEvent event) {

        System.out.println("=================================");
        System.out.println("Transaction event received");
        System.out.println(event);
        System.out.println("=================================");
    }
}