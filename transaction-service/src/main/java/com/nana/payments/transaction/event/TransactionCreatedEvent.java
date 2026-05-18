package com.nana.payments.transaction.event;

import java.math.BigDecimal;
import java.util.UUID;
import java.io.Serializable;

public record TransactionCreatedEvent(

                UUID transactionId,

                UUID accountId,

                BigDecimal amount

) implements Serializable {
}