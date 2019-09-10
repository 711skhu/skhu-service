package com.shouwn.com.skhuservice.exception;

import java.lang.annotation.Annotation;
import java.util.Map;

import com.shouwn.com.skhuservice.web.ApiResponse;
import com.shouwn.com.skhuservice.web.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(SkhuException.class)
	public ApiResponse skhuExceptionHandler(SkhuException e) {
		Annotation annotation = AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class);

		Map<String, Object> attributes = AnnotationUtils.getAnnotationAttributes(annotation);

		return CommonResponse.builder()
				.status((HttpStatus) attributes.get("code"))
				.message(e.getMessage())
				.build();
	}

	@ExceptionHandler(Exception.class)
	public ApiResponse otherExceptionHandler(Exception e) {
		log.error(e.getMessage(), e);

		Throwable rootCause = ExceptionUtils.getRootCause(e);

		ApiResponse response;

		if (rootCause instanceof SkhuException) {
			response = skhuExceptionHandler((SkhuException) rootCause);
		} else {
			response = CommonResponse.builder()
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.message("내부 오류")
					.build();
		}

		return response;
	}
}