package com.springBoot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.springBoot.exception.ApplicationError;
import com.springBoot.exception.EmployeeNotFoundException;

@ControllerAdvice
@RestController
public class ErrorHandler extends ResponseEntityExceptionHandler {

	@Value("unable to fetch from properties file")
	private String detail;
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<ApplicationError> handleEmployeeNotFoundException(EmployeeNotFoundException ex,
			WebRequest webRequest) {
		ApplicationError error = new ApplicationError();
		error.setCode(404);
		error.setMessage(ex.getMessage());
		error.setDetail(detail);
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

}
