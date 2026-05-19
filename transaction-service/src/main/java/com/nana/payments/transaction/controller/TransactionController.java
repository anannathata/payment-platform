package com.nana.payments.transaction.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.nana.payments.transaction.dto.CreateTransactionRequest;
import com.nana.payments.transaction.dto.TransactionResponse;
import com.nana.payments.transaction.service.TransactionService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create transaction", description = "Creates a new payment transaction")
    public TransactionResponse create(
            @RequestBody @Valid CreateTransactionRequest request,
            Authentication authentication) {
        return service.create(request, authentication);
    }
}