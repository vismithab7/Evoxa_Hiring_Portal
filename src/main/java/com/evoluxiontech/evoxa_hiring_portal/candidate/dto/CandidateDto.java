package com.evoluxiontech.evoxa_hiring_portal.candidate.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CandidateDto {
	@Size(min=3,max=50,message="Name Should be between 3~50 characters")
	private String name;
	@NotEmpty(message="Email is Required")
	@Email(message="Email Should be Proper")
	private String email;
	@Pattern(regexp="^.*(?=.{8,})(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$",message="Password should contain uppercase, lowercase, number, special character and min 8 digits")
	private String password;
	@NotNull(message= "Mobile number is required")
	@DecimalMin(value="6000000000", message="Enter Proper Mobile Number")
	@DecimalMax(value="9999999999", message="Enter Proper Mobile Number")
	private Long mobile;

}
