package com.evoluxiontech.evoxa_hiring_portal.util;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class EmailSender {
	 private final JavaMailSender mailSender;
	 
	 @Async
	 public void sendOtp(String email, int otp, String name, String role)
	 {
		 try {
	            SimpleMailMessage message = new SimpleMailMessage();
	            message.setTo(email);
	            message.setSubject("Hiring Portal - " + role + " OTP Verification");
	            message.setText("Hello " + name + ", your OTP is " + otp + ". It is valid for 5 minutes.");
	            mailSender.send(message);
	        } catch (Exception e) {
	            System.err.println("Registration OTP for " + email + " is: " + otp);
	            }
	 }
		 

	 public void sendForgotOtp(String email, int otp, String name, String role)
	 {
		 try {
	            SimpleMailMessage message = new SimpleMailMessage();
	            message.setTo(email);
	            message.setSubject("Hiring Portal - " + role + " Password Reset OTP");
	            message.setText("Hello " + name + ", your password reset OTP is " + otp +
	                    ". It is valid for 5 minutes.");
	            mailSender.send(message);
	        } catch (Exception e) {
	            System.err.println("Forgot-password OTP for " + email + " is: " + otp);
	        }
	    }
	 

}
