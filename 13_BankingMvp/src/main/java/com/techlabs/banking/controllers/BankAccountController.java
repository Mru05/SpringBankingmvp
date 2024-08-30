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

import com.techlabs.banking.dto.BankAccountDTO;
import com.techlabs.banking.entities.BankAccount;
import com.techlabs.banking.exception.BankAccountNotFoundException;
import com.techlabs.banking.exception.CustomerNotFoundException;
import com.techlabs.banking.exception.ErrorResponse;
import com.techlabs.banking.exception.PageResponse;
import com.techlabs.banking.service.BankAccountService;

@RestController
@RequestMapping("/api/bankaccounts")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @PostMapping
    public ResponseEntity<Object> createBankAccount(@Valid @RequestBody BankAccountDTO bankAccountDTO) {
        try {
            BankAccount createdBankAccount = bankAccountService.createBankAccount(bankAccountDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdBankAccount);
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage(), "CUSTOMER_NOT_FOUND"));
        }
    }

    @GetMapping
    public ResponseEntity<PageResponse<BankAccount>> getAllBankAccounts(Pageable pageable) {
        Page<BankAccount> bankAccounts = bankAccountService.getAllBankAccounts(pageable);
        return ResponseEntity.ok(PageResponse.of(bankAccounts));
    }

    @GetMapping("/{bankAccountId}")
    public ResponseEntity<Object> getBankAccountById(@PathVariable Long bankAccountId) {
        try {
            BankAccount bankAccount = bankAccountService.getBankAccountById(bankAccountId);
            return ResponseEntity.ok(bankAccount);
        } catch (BankAccountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage(), "BANK_ACCOUNT_NOT_FOUND"));
        }
    }

    @PutMapping("/{bankAccountId}")
    public ResponseEntity<Object> updateBankAccount(@PathVariable Long bankAccountId, @Valid @RequestBody BankAccountDTO bankAccountDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .findFirst()
                    .orElse("Invalid request parameters");
            return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), errorMessage, "INVALID_REQUEST"));
        }
        try {
            BankAccount updatedBankAccount = bankAccountService.updateBankAccount(bankAccountId, bankAccountDTO);
            return ResponseEntity.ok(updatedBankAccount);
        } catch (BankAccountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage(), "BANK_ACCOUNT_NOT_FOUND"));
        }
    }

    @DeleteMapping("/{bankAccountId}")
    public ResponseEntity<ErrorResponse> deleteBankAccount(@PathVariable Long bankAccountId) {
        try {
            bankAccountService.deleteBankAccount(bankAccountId);
            return ResponseEntity.noContent().build();
        } catch (BankAccountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage(), "BANK_ACCOUNT_NOT_FOUND"));
        }
    }
}
