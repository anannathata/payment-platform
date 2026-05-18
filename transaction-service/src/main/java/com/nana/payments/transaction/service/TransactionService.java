package com.nana.payments.transaction.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.nana.payments.transaction.dto.CreateTransactionRequest;
import com.nana.payments.transaction.dto.TransactionResponse;
import com.nana.payments.transaction.entity.Transaction;
import com.nana.payments.transaction.entity.TransactionStatus;
import com.nana.payments.transaction.repository.TransactionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository repository;

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

        return new TransactionResponse(
                savedTransaction.getId(),
                savedTransaction.getAccountId(),
                savedTransaction.getName(),
                savedTransaction.getAmount(),
                savedTransaction.getStatus(),
                savedTransaction.getCreatedAt());
    }
}