package com.techlabs.banking.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.techlabs.banking.dto.AdminCredentialsDto;
import com.techlabs.banking.entities.AdminCredentials;
import com.techlabs.banking.exception.AdminNotFoundException;
import com.techlabs.banking.repositories.AdminCredentialsRepository;

import org.springframework.stereotype.Service;

@Service
public class AdminCredentialsServiceImpl implements AdminCredentialsService {

    private final AdminCredentialsRepository adminCredentialsRepository;

    public AdminCredentialsServiceImpl(AdminCredentialsRepository adminCredentialsRepository) {
        this.adminCredentialsRepository = adminCredentialsRepository;
    }

    @Override
    public AdminCredentialsDto createAdmin(AdminCredentialsDto adminCredentialsDto) {
        AdminCredentials adminCredentials = new AdminCredentials();
        adminCredentials.setAdminUsername(adminCredentialsDto.getAdminUsername());
        adminCredentials.setPassword(adminCredentialsDto.getPassword());

        AdminCredentials savedAdmin = adminCredentialsRepository.save(adminCredentials);
        return new AdminCredentialsDto(savedAdmin); 
    }

    @Override
    public Page<AdminCredentials> getAdmins(Pageable pageable) {
        return adminCredentialsRepository.findAll(pageable);
    }

    @Override
    public AdminCredentialsDto getAdminById(Long adminId) { 
        AdminCredentials adminCredentials = adminCredentialsRepository.findById(adminId)
                .orElseThrow(() -> new AdminNotFoundException("Admin not found with ID: " + adminId));
        return new AdminCredentialsDto(adminCredentials); 
    }

    @Override
    public AdminCredentialsDto updateAdmin(Long adminId, AdminCredentialsDto adminCredentialsDto) { 
        AdminCredentials existingAdmin = adminCredentialsRepository.findById(adminId)
                .orElseThrow(() -> new AdminNotFoundException("Admin not found with ID: " + adminId));
        existingAdmin.setPassword(adminCredentialsDto.getPassword());
        AdminCredentials updatedAdmin = adminCredentialsRepository.save(existingAdmin);
        return new AdminCredentialsDto(updatedAdmin); 
    }

    @Override
    public void deleteAdmin(Long adminId) { 
        adminCredentialsRepository.deleteById(adminId);
    }
}