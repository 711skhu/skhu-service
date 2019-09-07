package com.shouwn.com.skhuservice.web.rental;

import com.shouwn.com.skhuservice.rental.DetailType;
import com.shouwn.com.skhuservice.rental.RoomType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LectureRoom {

	private String classroomName;
	private int people;
	private DetailType detailType;
	private RoomType roomType;

	@Builder
	public LectureRoom(String classroomName, int people, DetailType detailType, RoomType roomType) {
		this.detailType = detailType;
		this.roomType = roomType;
		this.classroomName = classroomName;
		this.people = people;
	}
}
