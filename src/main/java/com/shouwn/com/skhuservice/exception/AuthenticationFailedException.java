package com.shouwn.com.skhuservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AuthenticationFailedException extends SkhuException {

	public AuthenticationFailedException(String message) {
		super(message);
	}

	public AuthenticationFailedException(String message, Throwable cause) {
		super(message, cause);
	}
}
