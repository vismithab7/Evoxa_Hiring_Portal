package com.evoluxiontech.evoxa_hiring_portal.candidate.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeoutException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.evoluxiontech.evoxa_hiring_portal.candidate.dao.CandidateDao;
import com.evoluxiontech.evoxa_hiring_portal.candidate.dto.CandidateDto;
import com.evoluxiontech.evoxa_hiring_portal.candidate.dto.LoginDto;
import com.evoluxiontech.evoxa_hiring_portal.candidate.dto.OtpDto;
import com.evoluxiontech.evoxa_hiring_portal.candidate.dto.PasswordDto;
import com.evoluxiontech.evoxa_hiring_portal.candidate.entity.Candidate;
import com.evoluxiontech.evoxa_hiring_portal.candidate.service.AuthService;
import com.evoluxiontech.evoxa_hiring_portal.dto.ResponseDto;
import com.evoluxiontech.evoxa_hiring_portal.exception.DataExistsException;
import com.evoluxiontech.evoxa_hiring_portal.util.EmailSender;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
	private final CandidateDao dao;
    private final PasswordEncoder encoder;
    private final EmailSender emailSender;
//    private final AuthenticationManager authenticationManager;
//    private final UserDetailsService userDetailsService;
//    private final JwtUtil jwtUtil;

    private int otp() { return 100000 + new Random().nextInt(900000); }
	@Override
	public ResponseDto register(CandidateDto dto) {
	     if (!dao.emailUnique(dto.getEmail()))
	    	 throw new DataExistsException("Email Already Exists: " +dto.getEmail());
	     if (!dao.mobileUnique(dto.getMobile()))
	          throw new DataExistsException("Mobile Already Exists : " + dto.getMobile());
	     int code=otp();
	        emailSender.sendOtp(dto.getEmail(), code, dto.getName(), "CANDIDATE");

	        Candidate data = new Candidate();
	        data.setName(dto.getName());
	        data.setEmail(dto.getEmail());
	        data.setPassword(encoder.encode(dto.getPassword()));
	        data.setMobile(dto.getMobile());
	        data.setOtp(code);
	        data.setOtpExpiryTime(LocalDateTime.now().plusMinutes(5));
	        data.setStatus(false);
//	        Candidate savedData = dao.save(data);

//	        System.out.println("========== SAVED ID = " + savedData.getId());
//	        System.out.println("========== SAVED EMAIL = " + savedData.getEmail());
	        dao.save(data);
//
	        return new ResponseDto("Otp Sent Success, Verify within 5 minutes", dto);
	}

	@Override
	public ResponseDto verifyOtp(OtpDto dto) throws TimeoutException {
		 Candidate data=dao.findByEmail(dto.getEmail());
	        if (data.getOtpExpiryTime()==null || !LocalDateTime.now().isBefore(data.getOtpExpiryTime()))
	            throw new TimeoutException("Otp Expired, Resend Otp and Try Again");
	        if (dto.getOtp()!=data.getOtp())
	            throw new InputMismatchException("Otp miss match, Try Again");
	        data.setStatus(true);
	        data.setOtp(0);
	        data.setOtpExpiryTime(null);
	        dao.save(data);
	        return new ResponseDto("Account Created Success", data);
	}

	@Override
	public ResponseDto resendOtp(String email) {
		 Candidate data=dao.findByEmail(email);
	        int code=otp();
	        emailSender.sendOtp(email, code, data.getName(), "CANDIDATE");
	        data.setOtp(code);
	        data.setOtpExpiryTime(LocalDateTime.now().plusMinutes(5));
	        dao.save(data);
	        return new ResponseDto("Otp Resent Success valid only for 5 minutes",
	                Map.of("email", email));
	}

	@Override
	public ResponseDto forgotPassword(String email) {
		Candidate data=dao.findByEmail(email);
        int code=otp();
        emailSender.sendForgotOtp(email, code, data.getName(), "CANDIDATE");
        data.setOtp(code);
        data.setOtpExpiryTime(LocalDateTime.now().plusMinutes(5));
        dao.save(data);
        return new ResponseDto("Otp Sent Success valid only for 5 minutes",
                Map.of("email", email));
	}

	@Override
	public ResponseDto forgotPassword(PasswordDto dto) throws TimeoutException {
		Candidate data=dao.findByEmail(dto.getEmail());
        if (data.getOtpExpiryTime()==null || !LocalDateTime.now().isBefore(data.getOtpExpiryTime()))
            throw new TimeoutException("Otp Expired, Resend Otp and Try Again");
        if (dto.getOtp()!=data.getOtp())
            throw new InputMismatchException("Otp miss match, Try Again");
        data.setPassword(encoder.encode(dto.getPassword()));
        data.setOtp(0);
        data.setOtpExpiryTime(null);
        dao.save(data);
        return new ResponseDto("Password Updated Success", data);
	}

	@Override
	public ResponseDto login(LoginDto dto) {
	    Candidate data = dao.findByEmail(dto.getEmail());

	    if (data == null) {
	        throw new RuntimeException("Email not found");
	    }

	    if (!data.isStatus()) {
	        throw new RuntimeException("Please verify your account using OTP");
	    }

	    if (!encoder.matches(dto.getPassword(), data.getPassword())) {
	        throw new RuntimeException("Invalid password");
	    }

	    Map<String, String> result = new HashMap<>();
	    result.put("email", data.getEmail());
	    result.put("name", data.getName());

	    return new ResponseDto("Login Success", result);

//		authenticationManager.authenticate(
//	            new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
//	        UserDetails details=userDetailsService.loadUserByUsername(dto.getEmail());
//	        String token=jwtUtil.generateToken(details);
//	        Map<String,String> result=new HashMap<>();
//	        result.put("token", token);
//	        return new ResponseDto("Login Success", result);
	}

}
