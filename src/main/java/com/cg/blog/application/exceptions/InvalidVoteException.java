package com.cg.blog.application.exceptions;

public class InvalidVoteException extends RuntimeException {
	private String message;

	public InvalidVoteException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
