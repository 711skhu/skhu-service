package com.shouwn.com.skhuservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AlreadyExistException extends SkhuException {

	public AlreadyExistException(String message) {
		super(message);
	}

	public AlreadyExistException(String message, Throwable cause) {
		super(message, cause);
	}
}
