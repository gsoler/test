package com.king.config;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<?> handleConstraintViolation(ConstraintViolationException ex) {
		StringBuilder errors = new StringBuilder();
		for (ConstraintViolation<?> constraintViolation : ex.getConstraintViolations()) {
			if (errors.length() > 0) {
				errors.append("<br />");
			}
			errors.append(constraintViolation.getPropertyPath()).append(" ").append(constraintViolation.getMessage());
		}

		return new ResponseEntity<>(errors.toString(), HttpStatus.BAD_REQUEST);
	}

}
