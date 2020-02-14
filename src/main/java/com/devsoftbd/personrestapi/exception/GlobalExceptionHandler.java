package com.devsoftbd.personrestapi.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	protected ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		List<String> errors = new ArrayList<>();
		errors.add(request.getDescription(false));
		ErrorDetails errorDetails = new ErrorDetails(new Date(), HttpStatus.NOT_FOUND, ex.getMessage(), errors);
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		List<String> errors = new ArrayList<>();
		errors.add(ex.getVariableName() + " was missing");
		ErrorDetails errorDetails = new ErrorDetails(new Date(), HttpStatus.BAD_REQUEST, "Path variable was missing",
				errors);
		return new ResponseEntity<Object>(errorDetails, errorDetails.getStatus());
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> errors = new ArrayList<String>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		}
		for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
		}
		ErrorDetails errorDetails = new ErrorDetails(new Date(), HttpStatus.INTERNAL_SERVER_ERROR,
				"Entity validation failed", errors);
		return new ResponseEntity<Object>(errorDetails, errorDetails.getStatus());
	}

	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
	public ResponseEntity<?> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
			WebRequest request) {
		List<String> errors = new ArrayList<>();
		errors.add(ex.getName() + " should be of type " + ex.getRequiredType().getName());
		ErrorDetails errorDetails = new ErrorDetails(new Date(), HttpStatus.BAD_REQUEST,
				"Method argument type mismatch", errors);
		return new ResponseEntity<>(errorDetails, errorDetails.getStatus());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
		List<String> errors = new ArrayList<>();
		errors.add(request.getDescription(false));
		ErrorDetails errorDetails = new ErrorDetails(new Date(), HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(),
				errors);
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
