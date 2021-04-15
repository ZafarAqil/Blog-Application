package com.cg.blog.application.exceptions;

public class ModeratorApprovalException extends Exception {

	private static final long serialVersionUID = 1L;
	private String message;

	public ModeratorApprovalException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}