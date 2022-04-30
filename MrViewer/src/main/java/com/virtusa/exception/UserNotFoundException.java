package com.virtusa.exception;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 3849624047915047378L;

	@Override
	public String getMessage() {
		return "Wrong User";
	}

}
