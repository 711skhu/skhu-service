package com.shouwn.com.skhuservice.web;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import org.springframework.http.HttpStatus;

@Getter
@ToString
public class CommonResponse<T> implements ApiResponse<T> {

	private int code;

	private String status;

	private String message;

	private T data;

	@Builder
	public CommonResponse(HttpStatus status, String message, T data) {
		this.code = status.value();
		this.status = status.getReasonPhrase();
		this.message = message;
		this.data = data;
	}
}
