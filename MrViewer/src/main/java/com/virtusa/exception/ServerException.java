package com.virtusa.exception;

public class ServerException extends RuntimeException {

	private static final long serialVersionUID = -1364565163891669510L;

	public ServerException() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getMessage() {
		return "Error uploading file try again";
	}
}
