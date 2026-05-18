package com.nana.payments.transaction.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.nana.payments.transaction.dto.CreateTransactionRequest;
import com.nana.payments.transaction.dto.TransactionResponse;
import com.nana.payments.transaction.entity.Transaction;
import com.nana.payments.transaction.entity.TransactionStatus;
import com.nana.payments.transaction.repository.TransactionRepository;

import com.nana.payments.transaction.event.TransactionCreatedEvent;
import com.nana.payments.transaction.publisher.TransactionEventPublisher;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository repository;

    private final TransactionEventPublisher publisher;

    public TransactionResponse create(CreateTransactionRequest request) {

        Transaction transaction = Transaction.builder()
                .id(UUID.randomUUID())
                .accountId(request.accountId())
                .name(request.name())
                .amount(request.amount())
                .status(TransactionStatus.APPROVED)
                .createdAt(LocalDateTime.now())
                .build();

        Transaction savedTransaction = repository.save(transaction);

        publisher.publish(
                new TransactionCreatedEvent(
                        savedTransaction.getId(),
                        savedTransaction.getAccountId(),
                        savedTransaction.getAmount()));

        return new TransactionResponse(
                savedTransaction.getId(),
                savedTransaction.getAccountId(),
                savedTransaction.getName(),
                savedTransaction.getAmount(),
                savedTransaction.getStatus(),
                savedTransaction.getCreatedAt());
    }
}