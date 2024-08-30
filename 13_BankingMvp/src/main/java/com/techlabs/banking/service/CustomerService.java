package com.techlabs.banking.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.techlabs.banking.dto.CustomerDTO;
import com.techlabs.banking.entities.Customer;

public interface CustomerService {

    Customer createCustomer(CustomerDTO customerDTO); 

    Page<Customer> getAllCustomers(Pageable pageable);

    Customer getCustomerById(Long customerId);

    public Customer updateCustomer(Long CustomerId, CustomerDTO customerDTO);

    void deleteCustomer(Long customerId);
}