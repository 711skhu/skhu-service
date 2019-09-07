package com.shouwn.com.skhuservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AuthorizationFailedException extends SkhuException {

	public AuthorizationFailedException(String message) {
		super(message);
	}

	public AuthorizationFailedException(String message, Throwable cause) {
		super(message, cause);
	}
}
