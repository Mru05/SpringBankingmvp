package com.techlabs.banking.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techlabs.banking.dto.CustomerDTO;
import com.techlabs.banking.entities.Customer;
import com.techlabs.banking.exception.CustomerNotFoundException;
import com.techlabs.banking.repositories.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(CustomerDTO customerDTO) {
        Customer customer = Customer.builder()
                .firstName(customerDTO.getFirstName())
                .lastName(customerDTO.getLastName())
                .emailId(customerDTO.getEmailId())
                .password(customerDTO.getPassword())
                .build();
        return customerRepository.save(customer);
    }

    @Override
    public Page<Customer> getAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + customerId));
    }

    @Override
    public Customer updateCustomer(Long customerId, CustomerDTO customerDTO) {
        Optional<Customer> existingCustomer = customerRepository.findById(customerId);
        if (existingCustomer.isPresent()) {
            Customer customerToUpdate = existingCustomer.get();
            customerToUpdate.setFirstName(customerDTO.getFirstName());
            customerToUpdate.setLastName(customerDTO.getLastName());
            customerToUpdate.setEmailId(customerDTO.getEmailId());
            customerToUpdate.setPassword(customerDTO.getPassword()); 
            return customerRepository.save(customerToUpdate);
        } else {
            throw new CustomerNotFoundException("Customer not found with ID: " + customerId);
        }
    }

    @Override
    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }
}
