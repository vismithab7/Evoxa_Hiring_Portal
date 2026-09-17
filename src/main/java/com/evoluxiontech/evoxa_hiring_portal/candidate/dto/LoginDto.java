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
public class LoginDto {
	@NotEmpty(message = " Email is Required")
	@Email(message = "Email Should be Proper")
	private String email;
	@NotEmpty(message = "Password is required")
	private String password;

}
