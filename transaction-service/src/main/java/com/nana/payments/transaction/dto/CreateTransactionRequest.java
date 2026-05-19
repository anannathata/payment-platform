package com.nana.payments.transaction.dto;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record CreateTransactionRequest(

                @NotNull UUID accountId,
                @NotNull String name,
                @NotNull @DecimalMin("0.1") BigDecimal amount

) {
}