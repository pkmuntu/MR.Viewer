package com.virtusa.exception;

public class MovieNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 2734716318173340055L;

	@Override
	public String getMessage() {
		return "Enter movie not Present in Database";
	}
}
