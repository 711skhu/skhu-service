package com.shouwn.com.skhuservice.user;

import java.io.IOException;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.shouwn.com.skhuservice.exception.NotFoundException;
import com.shouwn.com.skhuservice.web.user.UserLoginRequest;
import org.apache.commons.lang3.exception.ExceptionUtils;

import org.springframework.stereotype.Service;

@Service
public class UserService {

	public HtmlPage login(UserLoginRequest loginRequest) {
		try {
			final WebClient webClient = new WebClient(BrowserVersion.INTERNET_EXPLORER);
			webClient.getOptions().setUseInsecureSSL(true);
			webClient.getOptions().setJavaScriptEnabled(true);
			webClient.getOptions().setCssEnabled(false);

			HtmlPage loginPage = webClient.getPage(UrlType.LOGINPAGE_URL.getUrl());
			HtmlForm loginForm = loginPage.getFormByName("");
			loginForm.getInputByName("txtID").setValueAttribute(loginRequest.getStudentNumber());
			loginForm.getInputByName("txtPW").setValueAttribute(loginRequest.getPassword());

			HtmlPage mainPage = loginForm.getInputByName("ibtnLogin").click();

			if (!UrlType.MAINPAGE_URL.getUrl().equals(mainPage.getUrl())) {
				throw new NotFoundException("로그인 실패. 계정 정보 확인 바랍니다.");
			}
			return mainPage;

		} catch (IOException e) {
			return ExceptionUtils.rethrow(e);
		}
	}

}
