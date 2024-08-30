package com.techlabs.banking.service;

import com.techlabs.banking.dto.BankAccountDTO;
import com.techlabs.banking.entities.BankAccount;
import com.techlabs.banking.entities.Customer;
import com.techlabs.banking.exception.BankAccountNotFoundException;
import com.techlabs.banking.exception.CustomerNotFoundException;
import com.techlabs.banking.repositories.BankAccountRepository;
import com.techlabs.banking.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public BankAccount createBankAccount(BankAccountDTO bankAccountDTO) {
        Customer customer = customerRepository.findById(bankAccountDTO.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + bankAccountDTO.getCustomerId()));

        // Generate a unique random account number (within the range of Long)
        Long accountNumber = generateRandomAccountNumber(); 

        BankAccount bankAccount = BankAccount.builder()
                .customer(customer)
                .accountNumber(accountNumber) // Now using Long
                .accountType(bankAccountDTO.getAccountType())
                .balance(bankAccountDTO.getBalance())
                .createdAt(LocalDateTime.now())
                .build();
        return bankAccountRepository.save(bankAccount);
    }

    @Override
    public Page<BankAccount> getAllBankAccounts(Pageable pageable) {
        return bankAccountRepository.findAll(pageable);
    }

    @Override
    public BankAccount getBankAccountById(Long bankAccountId) {
        return bankAccountRepository.findById(bankAccountId)
                .orElseThrow(() -> new BankAccountNotFoundException("Bank account not found with ID: " + bankAccountId));
    }

    @Override
    public BankAccount updateBankAccount(Long bankAccountId, BankAccountDTO bankAccountDTO) {
        BankAccount bankAccountToUpdate = bankAccountRepository.findById(bankAccountId)
                .orElseThrow(() -> new BankAccountNotFoundException("Bank account not found with ID: " + bankAccountId));

        bankAccountToUpdate.setAccountNumber(bankAccountDTO.getAccountNumber());
        bankAccountToUpdate.setAccountType(bankAccountDTO.getAccountType());
        bankAccountToUpdate.setBalance(bankAccountDTO.getBalance());

        return bankAccountRepository.save(bankAccountToUpdate);
    }

    @Override
    public void deleteBankAccount(Long bankAccountId) {
        bankAccountRepository.deleteById(bankAccountId);
    }

    public BankAccount getBankAccountByAccountNumber(Long accountNumber) { // Now using Long
        return bankAccountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new BankAccountNotFoundException("Bank account not found with account number: " + accountNumber));
    }

    // Helper method to generate a unique random account number (within the range of Long)
    private Long generateRandomAccountNumber() {
        Random random = new Random();
        Long accountNumber;
        do {
            // Generate a random Long within the desired range
            accountNumber = random.nextLong(999999999999L);  

            // Convert to a 12-digit string and back to Long
            accountNumber = Long.parseLong(String.format("%012d", accountNumber)); 
        } while (bankAccountRepository.existsByAccountNumber(accountNumber));
        return accountNumber;
    }
}