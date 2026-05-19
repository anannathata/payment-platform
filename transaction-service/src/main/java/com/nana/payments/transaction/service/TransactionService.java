package com.nana.payments.transaction.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.nana.payments.transaction.dto.CreateTransactionRequest;
import com.nana.payments.transaction.dto.TransactionResponse;
import com.nana.payments.transaction.entity.Transaction;
import com.nana.payments.transaction.entity.TransactionStatus;
import com.nana.payments.transaction.event.TransactionCreatedEvent;
import com.nana.payments.transaction.publisher.TransactionEventPublisher;
import com.nana.payments.transaction.repository.TransactionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService {

        private final TransactionRepository repository;
        private final TransactionEventPublisher publisher;

        public TransactionResponse create(
                        CreateTransactionRequest request,
                        Authentication authentication) {
                String createdBy = authentication.getName();

                Transaction transaction = Transaction.builder()
                                .id(UUID.randomUUID())
                                .accountId(request.accountId())
                                .name(request.name())
                                .amount(request.amount())
                                .status(TransactionStatus.APPROVED)
                                .createdAt(LocalDateTime.now())
                                .createdBy(createdBy)
                                .build();

                Transaction savedTransaction = repository.save(transaction);

                publishTransactionCreatedEvent(savedTransaction);

                return toResponse(savedTransaction);
        }

        private void publishTransactionCreatedEvent(Transaction transaction) {
                TransactionCreatedEvent event = new TransactionCreatedEvent(
                                transaction.getId(),
                                transaction.getAccountId(),
                                transaction.getAmount());

                publisher.publish(event);
        }

        private TransactionResponse toResponse(Transaction transaction) {
                return new TransactionResponse(
                                transaction.getId(),
                                transaction.getAccountId(),
                                transaction.getName(),
                                transaction.getAmount(),
                                transaction.getStatus(),
                                transaction.getCreatedAt(),
                                transaction.getCreatedBy());
        }
}