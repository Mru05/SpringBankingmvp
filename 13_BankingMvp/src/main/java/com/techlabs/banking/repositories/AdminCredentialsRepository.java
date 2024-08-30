package com.techlabs.banking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.banking.entities.AdminCredentials;

public interface AdminCredentialsRepository extends JpaRepository<AdminCredentials, Long>{

}
