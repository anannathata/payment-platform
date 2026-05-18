package com.nana.payments.transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nana.payments.transaction.entity.Transaction;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

}
