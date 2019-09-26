package com.shouwn.com.skhuservice.web.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserInfo {
	private String userName;

	@Builder
	public UserInfo(String userName) {
		this.userName = userName;
	}

}
