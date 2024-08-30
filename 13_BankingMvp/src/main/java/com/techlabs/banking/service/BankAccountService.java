package com.techlabs.banking.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.techlabs.banking.dto.BankAccountDTO;
import com.techlabs.banking.entities.BankAccount;

public interface BankAccountService {
	 BankAccount createBankAccount(BankAccountDTO bankAccountDTO);

	    Page<BankAccount> getAllBankAccounts(Pageable pageable);

	    BankAccount getBankAccountById(Long bankAccountId);

	    BankAccount updateBankAccount(Long bankAccountId, BankAccountDTO bankAccountDTO);

	    void deleteBankAccount(Long bankAccountId);
}
