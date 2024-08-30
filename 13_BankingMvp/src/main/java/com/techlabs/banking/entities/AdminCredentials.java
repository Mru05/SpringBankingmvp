package com.techlabs.banking.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "adminCredentials")
public class AdminCredentials {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name="adminId")
    private Long adminId;	
 

    @Column(name = "adminUsername",unique = true, nullable = false)
    private String adminUsername;

    @Column(name = "password", nullable = false)
    private String password;


}