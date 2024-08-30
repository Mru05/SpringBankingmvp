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
@Table(name = "transactions")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @NotBlank(message = "Sender Account Number cannot be blank")
    @Column(name = "sender_account_number", nullable = false, length = 12)
    private String senderAccountNumber;

    @NotBlank(message = "Receiver Account Number cannot be blank")
    @Column(name = "receiver_account_number", nullable = false, length = 12)
    private String receiverAccountNumber;

    @NotNull(message = "Amount cannot be null")
    @Min(value = 1, message = "Amount must be at least 1")
    @Column(name = "amount", nullable = false)
    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_of_transfer", nullable = false)
    private TransactionType typeOfTransfer;

    @Column(name = "transaction_date", nullable = false)
    private LocalDateTime transactionDate;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "bank_account_transactions",
            joinColumns = @JoinColumn(name = "transaction_id"),
            inverseJoinColumns = @JoinColumn(name = "bank_account_id")
    )
    private Set<BankAccount> involvedAccounts = new HashSet<>();
}