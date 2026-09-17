package com.evoluxiontech.evoxa_hiring_portal.exception;


import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.evoluxiontech.evoxa_hiring_portal.dto.ErrorDto;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public  ErrorDto validation(MethodArgumentNotValidException ex)
	{
		Map<String,String> errors = new HashMap<>();
		for (FieldError e : ex.getBindingResult().getFieldErrors()) 
		{
			errors.put(e.getField(), e.getDefaultMessage());
		}
		return new ErrorDto(errors);
		
	}
	
	@ExceptionHandler(DataExistsException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public ErrorDto exists(DataExistsException ex) 
	{ 
		return new ErrorDto(ex.getMessage()); 
	}
	@ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto notFound(DataNotFoundException ex) 
	{ 
		return new ErrorDto(ex.getMessage());
	}

    @ExceptionHandler(TimeoutException.class)
    @ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
    public ErrorDto timeout(TimeoutException ex) 
    { 
    	return new ErrorDto(ex.getMessage());
    }

    @ExceptionHandler(InputMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto mismatch(InputMismatchException ex) 
    { 
    	return new ErrorDto(ex.getMessage()); 
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorDto credentials(BadCredentialsException ex) 
    { 
    	return new ErrorDto("Invalid Email or Password"); 
    }
	

}
