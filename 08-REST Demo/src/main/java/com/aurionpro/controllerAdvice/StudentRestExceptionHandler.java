package com.aurionpro.controllerAdvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.aurionpro.entity.StudentErrorResponse;
import com.aurionpro.exception.StudentNotFoundException;

@ControllerAdvice
public class StudentRestExceptionHandler {

	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<StudentErrorResponse> handleStudentNotFoundException(StudentNotFoundException e){
		StudentErrorResponse error=new StudentErrorResponse();
	
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setTimestamp(System.currentTimeMillis());
		error.setMsg(e.getMessage());
		
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<StudentErrorResponse> handleException(Exception e){
		StudentErrorResponse error=new StudentErrorResponse();
	
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setTimestamp(System.currentTimeMillis());
		error.setMsg(e.getMessage());
		
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
}
