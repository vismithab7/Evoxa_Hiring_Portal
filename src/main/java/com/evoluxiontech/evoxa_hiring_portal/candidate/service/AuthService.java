package com.evoluxiontech.evoxa_hiring_portal.candidate.service;

import java.util.concurrent.TimeoutException;

import com.evoluxiontech.evoxa_hiring_portal.candidate.dto.CandidateDto;
import com.evoluxiontech.evoxa_hiring_portal.candidate.dto.LoginDto;
import com.evoluxiontech.evoxa_hiring_portal.candidate.dto.OtpDto;
import com.evoluxiontech.evoxa_hiring_portal.candidate.dto.PasswordDto;
import com.evoluxiontech.evoxa_hiring_portal.dto.ResponseDto;

public interface AuthService {
	ResponseDto register(CandidateDto dto);
	ResponseDto verifyOtp(OtpDto dto) throws TimeoutException;
	ResponseDto resendOtp(String email);
    ResponseDto forgotPassword(String email);
    ResponseDto forgotPassword(PasswordDto dto) throws TimeoutException;
    ResponseDto login(LoginDto dto);
}
	
	


