package com.techlabs.banking.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.banking.entities.BankAccount;
import com.techlabs.banking.entities.Customer;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long>{

	Optional<BankAccount> findByAccountNumber(Long accountNumber);
	boolean existsByAccountNumber(Long accountNumber);

}
