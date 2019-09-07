package com.shouwn.com.skhuservice.web.rental;

import java.time.LocalDate;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RentalDate {
	private int startTime;
	private int endTime;
	private LocalDate rentalDate;

	@Builder
	public RentalDate(int startTime, int endTime, LocalDate rentalDate) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.rentalDate = rentalDate;
	}
}

