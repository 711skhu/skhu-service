package com.shouwn.com.skhuservice.rental;

import lombok.Getter;

@Getter
public enum DetailType {

	PROJECTOR("빔프로젝터"),
	COMPUTER("컴퓨터"),
	BOTH("컴퓨터", "빔프로젝터"),
	NULL("없음");

	private String[] details;

	DetailType(String... details) {
		this.details = details;
	}
}
