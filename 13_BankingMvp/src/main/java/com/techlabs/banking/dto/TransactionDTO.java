package com.techlabs.banking.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.*;

import com.techlabs.banking.entities.TransactionType;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

    private Long transactionId; 

    @NotBlank(message = "Sender Account Number cannot be blank")
    private String senderAccountNumber;

    private String receiverAccountNumber; 

    @NotNull(message = "Amount cannot be null")
    @Min(value = 1, message = "Amount must be at least 1")
    private Double amount;

    @NotBlank(message = "Transaction Type cannot be blank")
    private TransactionType typeOfTransfer; 

}