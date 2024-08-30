package com.techlabs.banking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.banking.entities.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
