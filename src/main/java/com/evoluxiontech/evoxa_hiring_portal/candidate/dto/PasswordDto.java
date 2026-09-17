package com.evoluxiontech.evoxa_hiring_portal.candidate.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PasswordDto {
	@NotEmpty
	@Email
	private String email;
	@NotEmpty
	private String password;
	private int otp;
	

}
