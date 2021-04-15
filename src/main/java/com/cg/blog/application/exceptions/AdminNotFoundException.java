package com.cg.blog.application.exceptions;

public class AdminNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String message;

	public AdminNotFoundException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}