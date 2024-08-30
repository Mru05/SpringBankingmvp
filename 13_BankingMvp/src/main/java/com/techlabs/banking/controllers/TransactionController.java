package com.techlabs.banking.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.banking.dto.TransactionDTO;
import com.techlabs.banking.entities.Transaction;
import com.techlabs.banking.exception.BankAccountNotFoundException;
import com.techlabs.banking.exception.ErrorResponse;
import com.techlabs.banking.exception.PageResponse;
import com.techlabs.banking.exception.TransactionNotFoundException;
import com.techlabs.banking.service.TransactionService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Object> createTransaction(@Valid @RequestBody TransactionDTO transactionDTO) {
        try {
            Transaction createdTransaction = transactionService.createTransaction(transactionDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTransaction);
        } catch (BankAccountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage(), "BANK_ACCOUNT_NOT_FOUND"));
        }
    }

    @GetMapping
    public ResponseEntity<PageResponse<Transaction>> getAllTransactions(Pageable pageable) {
        Page<Transaction> transactions = transactionService.getAllTransactions(pageable);
        return ResponseEntity.ok(PageResponse.of(transactions));
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<Object> getTransactionById(@PathVariable Long transactionId) {
        try {
            Transaction transaction = transactionService.getTransactionById(transactionId);
            return ResponseEntity.ok(transaction);
        } catch (TransactionNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage(), "TRANSACTION_NOT_FOUND"));
        }
    }

    @PutMapping("/{transactionId}")
    public ResponseEntity<Object> updateTransaction(@PathVariable Long transactionId, @Valid @RequestBody TransactionDTO transactionDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .findFirst()
                    .orElse("Invalid request parameters");
            return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), errorMessage, "INVALID_REQUEST"));
        }
        try {
            Transaction updatedTransaction = transactionService.updateTransaction(transactionId, transactionDTO);
            return ResponseEntity.ok(updatedTransaction);
        } catch (TransactionNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage(), "TRANSACTION_NOT_FOUND"));
        }
    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<ErrorResponse> deleteTransaction(@PathVariable Long transactionId) {
        try {
            transactionService.deleteTransaction(transactionId);
            return ResponseEntity.noContent().build();
        } catch (TransactionNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage(), "TRANSACTION_NOT_FOUND"));
        }
    }
}