package com.shouwn.com.skhuservice.web.rental;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LectureRentalInfo {
	private int idx;
	private String rentalState;
	private RentalDate rentalDate;

	@Builder
	public LectureRentalInfo(int idx, String rentalState, RentalDate rentalDate) {
		this.idx = idx;
		this.rentalState = rentalState;
		this.rentalDate = rentalDate;
	}
}
