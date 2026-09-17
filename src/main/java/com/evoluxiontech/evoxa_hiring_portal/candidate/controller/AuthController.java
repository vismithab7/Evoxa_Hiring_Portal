package com.evoluxiontech.evoxa_hiring_portal.candidate.controller;

import java.util.concurrent.TimeoutException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.evoluxiontech.evoxa_hiring_portal.candidate.dto.CandidateDto;
import com.evoluxiontech.evoxa_hiring_portal.candidate.dto.LoginDto;
import com.evoluxiontech.evoxa_hiring_portal.candidate.dto.OtpDto;
import com.evoluxiontech.evoxa_hiring_portal.candidate.dto.PasswordDto;
import com.evoluxiontech.evoxa_hiring_portal.candidate.entity.Candidate;
import com.evoluxiontech.evoxa_hiring_portal.candidate.service.AuthService;
import com.evoluxiontech.evoxa_hiring_portal.dto.ResponseDto;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/candidate/auth")
public class AuthController {

	private final AuthService authService;
	
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseDto register(@Valid @RequestBody CandidateDto dto)	
	{
		return authService.register(dto);
		
	}
	@PostMapping("/verify-otp")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto verifyOtp(@RequestBody OtpDto dto) throws TimeoutException {
        return authService.verifyOtp(dto);
    }
	
	 @GetMapping("/resend-otp")
	    public ResponseDto resendOtp(@RequestParam String email) {
	        return authService.resendOtp(email);
	    }

	    @GetMapping("/forgot-password")
	    public ResponseDto forgotPassword(@RequestParam String email) {
	        return authService.forgotPassword(email);
	    }

	    @PostMapping("/forgot-password")
	    public ResponseDto resetPassword(@Valid @RequestBody PasswordDto dto) throws TimeoutException {
	        return authService.forgotPassword(dto);
	    }

	    @PostMapping("/login")
	    public ResponseDto login(@Valid @RequestBody LoginDto dto) {
	        return authService.login(dto);
	    }
	
	
}
