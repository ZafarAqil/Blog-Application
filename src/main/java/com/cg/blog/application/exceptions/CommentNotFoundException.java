package com.cg.blog.application.exceptions;

public class CommentNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String message;

	public CommentNotFoundException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
