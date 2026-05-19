package com.nana.payments.transaction.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
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
import org.springframework.security.core.Authentication;

import com.nana.payments.transaction.dto.CreateTransactionRequest;
import com.nana.payments.transaction.dto.TransactionResponse;
import com.nana.payments.transaction.entity.Transaction;
import com.nana.payments.transaction.entity.TransactionStatus;
import com.nana.payments.transaction.event.TransactionCreatedEvent;
import com.nana.payments.transaction.publisher.TransactionEventPublisher;
import com.nana.payments.transaction.repository.TransactionRepository;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private TransactionRepository repository;

    @Mock
    private TransactionEventPublisher publisher;

    @InjectMocks
    private TransactionService service;

    private CreateTransactionRequest request;
    private Authentication authentication;

    @BeforeEach
    void setup() {
        request = new CreateTransactionRequest(
                UUID.randomUUID(),
                "Purchase",
                BigDecimal.valueOf(299.90));

        authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("admin");
    }

    @Test
    void shouldCreateTransactionSuccessfully() {
        Transaction savedTransaction = Transaction.builder()
                .id(UUID.randomUUID())
                .accountId(request.accountId())
                .name(request.name())
                .amount(request.amount())
                .status(TransactionStatus.APPROVED)
                .createdAt(LocalDateTime.now())
                .createdBy("admin")
                .build();

        when(repository.save(any(Transaction.class)))
                .thenReturn(savedTransaction);

        TransactionResponse response = service.create(request, authentication);

        assertEquals(savedTransaction.getId(), response.id());
        assertEquals(request.accountId(), response.accountId());
        assertEquals("Purchase", response.name());
        assertEquals(BigDecimal.valueOf(299.90), response.amount());
        assertEquals(TransactionStatus.APPROVED, response.status());
        assertEquals("admin", response.createdBy());

        verify(repository).save(any(Transaction.class));
        verify(publisher).publish(any(TransactionCreatedEvent.class));
    }
}