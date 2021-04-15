package com.cg.blog.application.exceptions;

public class CommunityNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private String message;

	public CommunityNotFoundException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
