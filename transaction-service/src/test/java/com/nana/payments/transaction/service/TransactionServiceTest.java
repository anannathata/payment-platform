package com.nana.payments.transaction.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nana.payments.transaction.dto.CreateTransactionRequest;
import com.nana.payments.transaction.dto.TransactionResponse;
import com.nana.payments.transaction.entity.Transaction;
import com.nana.payments.transaction.entity.TransactionStatus;
import com.nana.payments.transaction.repository.TransactionRepository;
import com.nana.payments.transaction.publisher.TransactionEventPublisher;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private TransactionRepository repository;

    @Mock
    private TransactionEventPublisher publisher;

    @InjectMocks
    private TransactionService service;

    private CreateTransactionRequest request;

    @BeforeEach
    void setup() {

        request = new CreateTransactionRequest(
                UUID.randomUUID(),
                "Purchase",
                BigDecimal.valueOf(299.90));
    }

    @Test
    void shouldCreateTransactionSuccessfully() {

        Transaction transaction = Transaction.builder()
                .id(UUID.randomUUID())
                .accountId(request.accountId())
                .name(request.name())
                .amount(request.amount())
                .status(TransactionStatus.APPROVED)
                .createdAt(LocalDateTime.now())
                .build();

        when(repository.save(any(Transaction.class)))
                .thenReturn(transaction);

        TransactionResponse response = service.create(request);

        assertEquals("Purchase", response.name());
        assertEquals(TransactionStatus.APPROVED, response.status());
    }
}