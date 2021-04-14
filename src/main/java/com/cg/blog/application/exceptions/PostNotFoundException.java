package com.cg.blog.application.exceptions;

public class PostNotFoundException extends RuntimeException {

	private String message;

	public PostNotFoundException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
