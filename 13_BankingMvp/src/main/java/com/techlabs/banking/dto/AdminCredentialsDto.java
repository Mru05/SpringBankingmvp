package com.techlabs.banking.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.techlabs.banking.entities.AdminCredentials;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminCredentialsDto {

	
	 @NotNull(message = "Admin Username cannot be null")
	 @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Company Name must contain only letters")
	    private String adminUsername;

	 @NotNull(message = "Password cannot be null")
	    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d@]{8,}$", 
	             message = "Password must contain at least 8 characters, one uppercase letter, one lowercase letter, and one number")
private String password;
	 
	 public AdminCredentialsDto(AdminCredentials adminCredentials) {
	        this.adminUsername = adminCredentials.getAdminUsername();
	        this.password = adminCredentials.getPassword();
	 }
}
