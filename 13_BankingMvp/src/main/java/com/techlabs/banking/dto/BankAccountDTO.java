package com.techlabs.banking.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountDTO {

    private Long bankAccountId; 

    private Long customerId; // For linking to a customer

    @NotBlank(message = "Account Number cannot be blank")
    private Long accountNumber;

    @NotBlank(message = "Account Type cannot be blank")
    private String accountType;

    @NotNull(message = "Balance cannot be null")
    @Min(value = 500, message = "Minimum balance should be 500")
    private Double balance;

   
}
