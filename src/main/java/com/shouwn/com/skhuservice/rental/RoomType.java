package com.shouwn.com.skhuservice.rental;

import lombok.Getter;

@Getter
public enum RoomType {

	SPECIAL("특수강의실"),
	SMALL("소형강의실"),
	MIDDLE("중형강의실"),
	BIG("대형강의실");

	private String room;

	RoomType(String room) {
		this.room = room;
	}
}
