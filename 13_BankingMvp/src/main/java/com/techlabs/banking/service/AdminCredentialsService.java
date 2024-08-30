package com.techlabs.banking.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.techlabs.banking.dto.AdminCredentialsDto;
import com.techlabs.banking.entities.AdminCredentials;

public interface AdminCredentialsService {
	 AdminCredentialsDto createAdmin(AdminCredentialsDto adminCredentialsDto);

	    Page<AdminCredentials> getAdmins(Pageable pageable);

	    AdminCredentialsDto getAdminById(Long adminId); 

	    AdminCredentialsDto updateAdmin(Long adminId, AdminCredentialsDto adminCredentialsDto); 

	    void deleteAdmin(Long adminId); 
	

}
