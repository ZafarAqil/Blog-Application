package com.cg.blog.application.exceptions;

public class BloggerNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public BloggerNotFoundException(String message) {
		super(message);
	}
}