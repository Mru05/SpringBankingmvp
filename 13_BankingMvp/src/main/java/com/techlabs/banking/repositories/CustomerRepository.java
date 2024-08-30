package com.techlabs.banking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.techlabs.banking.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
	

}
