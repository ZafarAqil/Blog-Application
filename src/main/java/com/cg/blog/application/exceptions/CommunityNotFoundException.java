package com.cg.blog.application.exceptions;

public class CommunityNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	public CommunityNotFoundException(String message) {
		super(message);
	}
}
