package com.cg.blog.application.exceptions;

public class InvalidVoteException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public InvalidVoteException(String message) {
		super(message);
	}
}
