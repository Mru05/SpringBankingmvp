package com.techlabs.banking.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "bank_accounts")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bankAccountId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @NotBlank(message = "Account Number cannot be blank")
    @Column(name = "account_number", unique = true, nullable = false, length = 12)
    private Long accountNumber;

    @NotBlank(message = "Account Type cannot be blank")
    @Column(name = "account_type", nullable = false)
    private String accountType;

    @NotNull(message = "Balance cannot be null")
    @Min(value = 500, message = "Minimum balance should be 500")
    @Column(name = "balance", nullable = false)
    private Double balance;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}