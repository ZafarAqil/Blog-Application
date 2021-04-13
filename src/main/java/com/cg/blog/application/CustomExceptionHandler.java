package com.cg.blog.application;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cg.blog.application.exceptions.IdNotFoundException;

@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(IdNotFoundException.class)
	public final ResponseEntity<Object> handleIdNotFoundException(IdNotFoundException e){
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		
	}
}
