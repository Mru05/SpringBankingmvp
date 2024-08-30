package com.techlabs.banking.controllers;

import com.techlabs.banking.dto.AdminCredentialsDto;
import com.techlabs.banking.entities.AdminCredentials;
import com.techlabs.banking.service.AdminCredentialsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin")
public class AdminCredentialsController {

    private final AdminCredentialsService adminCredentialsService;

    public AdminCredentialsController(AdminCredentialsService adminCredentialsService) {
        this.adminCredentialsService = adminCredentialsService;
    }

    // POST: /api/admin
    @PostMapping
    public ResponseEntity<AdminCredentialsDto> createAdmin(@Valid @RequestBody AdminCredentialsDto adminCredentialsDto) {
        AdminCredentialsDto createdAdmin = adminCredentialsService.createAdmin(adminCredentialsDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAdmin);
    }

    // GET: /api/admin
    @GetMapping
    public ResponseEntity<Page<AdminCredentials>> getAdmins(Pageable pageable) {
        Page<AdminCredentials> admins = adminCredentialsService.getAdmins(pageable);
        return ResponseEntity.ok(admins);
    }

    // GET: /api/admin/{adminUsername}
    @GetMapping("/{adminUsername}")
    public ResponseEntity<AdminCredentialsDto> getAdminById(@PathVariable Long adminUsername) {
        AdminCredentialsDto admin = adminCredentialsService.getAdminById(adminUsername);
        return ResponseEntity.ok(admin);
    }

    // PUT: /api/admin/{adminUsername}
    @PutMapping("/{adminUsername}")
    public ResponseEntity<AdminCredentialsDto> updateAdmin(
            @PathVariable Long adminUsername,
            @Valid @RequestBody AdminCredentialsDto adminCredentialsDto) {
        AdminCredentialsDto updatedAdmin = adminCredentialsService.updateAdmin(adminUsername, adminCredentialsDto);
        return ResponseEntity.ok(updatedAdmin);
    }

    // DELETE: /api/admin/{adminUsername}
    @DeleteMapping("/{adminUsername}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long adminUsername) {
        adminCredentialsService.deleteAdmin(adminUsername);
        return ResponseEntity.noContent().build();
    }
}