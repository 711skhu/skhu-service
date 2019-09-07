package com.shouwn.com.skhuservice.web;

public interface ApiResponse<T> {

	int getCode();

	/**
	 * REST API Status 코드
	 *
	 * @return Status 코드
	 */
	String getStatus();

	/**
	 * 프론트엔드 개발의 편의를 위한 서버측의 메세지
	 *
	 * @return 서버 측의 메세지
	 */
	String getMessage();

	/**
	 * API 응답 데이터
	 *
	 * @return 데이터
	 */
	T getData();
}
