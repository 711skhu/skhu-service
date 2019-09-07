package com.shouwn.com.skhuservice.exception;

class SkhuException extends RuntimeException {

	SkhuException(String message) {
		super(message);
	}

	SkhuException(String message, Throwable cause) {
		super(message, cause);
	}
}
