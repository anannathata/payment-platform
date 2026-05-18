package com.nana.payments.notification.event;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

public record TransactionCreatedEvent(

                UUID transactionId,

                UUID accountId,

                BigDecimal amount

) implements Serializable {
}