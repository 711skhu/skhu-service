package com.shouwn.com.skhuservice.web.rental;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RentalRequest {
	private String startTime;
	private String endTime;
	private String reason;
	private String peopleList;
	private String phoneNumber;
}
