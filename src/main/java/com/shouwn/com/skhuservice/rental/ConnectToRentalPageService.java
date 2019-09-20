package com.shouwn.com.skhuservice.rental;

import java.io.IOException;

import com.gargoylesoftware.htmlunit.html.*;
import com.shouwn.com.skhuservice.exception.NotFoundException;
import com.shouwn.com.skhuservice.user.UrlType;
import org.apache.commons.lang3.exception.ExceptionUtils;

import org.springframework.stereotype.Service;

@Service
public class ConnectToRentalPageService {

	public HtmlPage connectToRentalPage(HtmlPage uniMyMainPage) {
		try {
			HtmlPage mainPage = getPage(uniMyMainPage);
			getLeftFrameBody(mainPage);
			HtmlPage contentPage = getPage(mainPage);

			if (UrlType.RENTALPAGE_URL.getUrl().equals(contentPage.getUrl())) {
				return contentPage;
			} else {
				throw new NotFoundException("대여 페이지 연결 실패");
			}

		} catch (IOException e) {
			return ExceptionUtils.rethrow(e);
		}
	}

	private HtmlPage getPage(HtmlPage mainPage) { //메인페이지로 들어가는 코드
		HtmlElement frameset = mainPage.getBody(); //frameset 으로 입력 받음
		DomNode frameNode = frameset.getLastChild().getPreviousSibling(); //마지막 frameset에서 mainFrame 들어와서 leftFrame 입장
		HtmlFrame frame = (HtmlFrame) frameNode;
		HtmlPage page = (HtmlPage) frame.getEnclosedPage();

		return page;
	}

	private void getLeftFrameBody(HtmlPage mainPage) throws IOException { //메뉴바로 들어왔음
		HtmlElement mainFrameset = mainPage.getBody(); //leftFrame의 전부를 받기
		DomNode leftFrameNode = mainFrameset.getFirstChild().getNextSibling();//메뉴바에서 웹서비스 누르고 시설물 대여신청으로 들어가기
		HtmlFrame leftFrame = (HtmlFrame) leftFrameNode;
		HtmlPage leftFrameBody = (HtmlPage) leftFrame.getEnclosedPage();
		clickMenu(leftFrameBody);

		return;
	}

	private void clickMenu(HtmlPage leftPage) throws IOException {
		HtmlAnchor webServiceAnchor = leftPage.getAnchorByText("웹서비스");
		leftPage = webServiceAnchor.click();
		HtmlAnchor rentPageAnchor = leftPage.getAnchorByText("[N]시설물대여 신청");
		rentPageAnchor.click();

		return;
	}

	public HtmlPage topPage(HtmlPage uniMyMainPage) {
		HtmlPage mainPage = getTopPage(uniMyMainPage);
		HtmlPage contentPage = getPage(mainPage);

		if (UrlType.RENTALPAGE_URL.getUrl().equals(contentPage.getUrl())) {
			return contentPage;
		} else {
			throw new NotFoundException("사용자 정보 제공 페이지 연결 실패");
		}

	}

	private HtmlPage getTopPage(HtmlPage topPage) {
		HtmlElement frameset = topPage.getBody(); //frameset으로 받음
		DomNode frameNode = frameset.getFirstChild(); //마지막 frameset에서 topframe까지의 접근
		HtmlFrame frame = (HtmlFrame) frameNode;
		HtmlPage page = (HtmlPage) frame.getEnclosedPage();

		return page;
	}
}
