package com.cg.blog.application.exceptions;

public class BloggerNotFoundException extends RuntimeException {

	private String message;

	public BloggerNotFoundException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}