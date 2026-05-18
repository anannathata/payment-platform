package com.nana.payments.transaction.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.nana.payments.transaction.entity.TransactionStatus;

public record TransactionResponse(

        UUID id,

        UUID accountId,

        String name,

        BigDecimal amount,

        TransactionStatus status,

        LocalDateTime createdAt

) {
}