package com.techlabs.banking.service;

import com.techlabs.banking.dto.TransactionDTO;
import com.techlabs.banking.entities.Transaction;

import com.techlabs.banking.exception.TransactionNotFoundException;
import com.techlabs.banking.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Transaction createTransaction(TransactionDTO transactionDTO) {
    

        Transaction transaction = Transaction.builder()
                .senderAccountNumber(transactionDTO.getSenderAccountNumber())
                .receiverAccountNumber(transactionDTO.getReceiverAccountNumber())
                .amount(transactionDTO.getAmount())
                .typeOfTransfer(transactionDTO.getTypeOfTransfer())
                .transactionDate(LocalDateTime.now())
                .build();
        return transactionRepository.save(transaction);
    }

    @Override
    public Page<Transaction> getAllTransactions(Pageable pageable) {
        return transactionRepository.findAll(pageable);
    }

    @Override
    public Transaction getTransactionById(Long transactionId) {
        return transactionRepository.findById(transactionId)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction not found with ID: " + transactionId));
    }

    @Override
    public Transaction updateTransaction(Long transactionId, TransactionDTO transactionDTO) {
        Optional<Transaction> existingTransaction = transactionRepository.findById(transactionId);
        if (existingTransaction.isPresent()) {
            Transaction transactionToUpdate = existingTransaction.get();

            return transactionRepository.save(transactionToUpdate);
        } else {
            throw new TransactionNotFoundException("Transaction not found with ID: " + transactionId);
        }
    }

    @Override
    public void deleteTransaction(Long transactionId) {
        transactionRepository.deleteById(transactionId);
    }
}