package com.cg.blog.application.exceptions;

public class CommentNotFoundException extends RuntimeException {

	private String message;

	public CommentNotFoundException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
