package com.techlabs.banking.entities;

import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customer")
@Data 
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "emailId", unique = true, nullable = false)
    private String emailId;

    @Column(name = "password", nullable = false)
    private String password; 
    
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true) 
    private Set<BankAccount> bankAccounts;
}
