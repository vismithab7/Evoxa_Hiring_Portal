package com.evoluxiontech.evoxa_hiring_portal.candidate.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    @Column
	private String name;
	@Column(unique = true, nullable = false)
	private String email;
	@Column
	private String password;
	@Column(unique = true, nullable = false)
	private Long mobile;
	private int otp;
	private LocalDateTime otpExpiryTime;
	private boolean status;
	private LocalDateTime createdTime;
	
	@PrePersist
	public void beforeCreate() {
		if (createdTime == null) createdTime = LocalDateTime.now();
	}
	
	
} 
