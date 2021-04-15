package com.cg.blog.application.exceptions;

public class NotEnoughPointsException extends Exception {

	private static final long serialVersionUID = 1L;
	private String message;

	public NotEnoughPointsException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
