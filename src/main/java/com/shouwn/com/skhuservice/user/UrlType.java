package com.shouwn.com.skhuservice.user;

import java.net.MalformedURLException;
import java.net.URL;

import lombok.Getter;

@Getter
public enum UrlType {
	FORESTBASE_URL("https://forest.skhu.ac.kr"),
	LOGINPAGE_URL("https://forest.skhu.ac.kr/Gate/UniLogin.aspx"),
	MAINPAGE_URL("https://forest.skhu.ac.kr/Gate/UniMyMain.aspx"),
	TOPPAGE_URL("https://forest.skhu.ac.kr/Gate/UniTopMenu.aspx"),
	RENTALPAGE_URL("https://forest.skhu.ac.kr/Gate/SAM/Lesson/G/SSEG20P.aspx?&maincd=O&systemcd=S&seq=100");

	private final URL url;

	UrlType(String url) {
		try {
			this.url = new URL(url);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}
}
