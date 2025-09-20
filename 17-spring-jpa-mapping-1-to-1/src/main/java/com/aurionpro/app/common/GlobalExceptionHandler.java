package com.aurionpro.app.common;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.aurionpro.app.exception.NotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ApiError> handleNotFound(NotFoundException ex, HttpServletRequest req) {
		ApiError err = new ApiError();
		err.setStatus(HttpStatus.NOT_FOUND.value());
		err.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
		err.setMessage(ex.getMessage());
		err.setPath(req.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest req) {
		ApiError err = new ApiError();
		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
		err.setMessage("Validation failed");
		err.setPath(req.getRequestURI());
		List<String> details = ex.getBindingResult().getFieldErrors().stream()
				.map(fe -> fe.getField() + ": " + fe.getDefaultMessage()).toList();
		err.setValidationErrors(details);
		return ResponseEntity.badRequest().body(err);
	}

	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<ApiError> handleState(IllegalStateException ex, HttpServletRequest req) {
		ApiError err = new ApiError();
		err.setStatus(HttpStatus.CONFLICT.value());
		err.setError(HttpStatus.CONFLICT.getReasonPhrase());
		err.setMessage(ex.getMessage());
		err.setPath(req.getRequestURI());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiError> handleGeneric(Exception ex, HttpServletRequest req) {
		ApiError err = new ApiError();
		err.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		err.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		err.setMessage(ex.getMessage());
		err.setPath(req.getRequestURI());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
	}
}
