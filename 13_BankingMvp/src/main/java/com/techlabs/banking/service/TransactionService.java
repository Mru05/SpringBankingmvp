package com.techlabs.banking.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.techlabs.banking.dto.TransactionDTO;
import com.techlabs.banking.entities.Transaction;

public interface TransactionService {
	
	 Transaction createTransaction(TransactionDTO transactionDTO);

	    Page<Transaction> getAllTransactions(Pageable pageable);

	    Transaction getTransactionById(Long transactionId);

	    Transaction updateTransaction(Long transactionId, TransactionDTO transactionDTO);

	    void deleteTransaction(Long transactionId);

}
