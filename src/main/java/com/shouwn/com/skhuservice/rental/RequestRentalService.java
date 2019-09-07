package com.shouwn.com.skhuservice.rental;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.html.*;
import com.shouwn.com.skhuservice.exception.InvalidParameterException;
import com.shouwn.com.skhuservice.user.UrlType;
import com.shouwn.com.skhuservice.web.rental.RentalDate;
import com.shouwn.com.skhuservice.web.rental.RentalRequest;
import org.apache.commons.lang3.exception.ExceptionUtils;

import org.springframework.stereotype.Service;

@Service
public class RequestRentalService {

	public HtmlPage requestRental(HtmlPage rentalPage, RentalRequest rentalRequest) {
		if (!UrlType.RENTALPAGE_URL.getUrl().equals(rentalPage.getUrl())) {
			throw new IllegalStateException("잘못된 접근 입니다.");
		}

		try {
			List<RentalDate> rentalList = new ArrayList<>();
			HtmlTable rentalListTable = (HtmlTable) rentalPage.getElementById("gv시설대여현황");

			for (int i = 1; i < rentalListTable.getRowCount(); i++) {
				String rowRentalDate = rentalListTable.getCellAt(i, 1).asText();
				if (rentalListTable.getCellAt(i, 2).asText().equals("제한") && rowRentalDate.substring(30, 32).equals("09")) {
					rentalList.add(new RentalDate(Integer.parseInt(rowRentalDate.substring(11, 13)), Integer.parseInt(rowRentalDate.substring(30, 32)) - 1, LocalDate.parse(rowRentalDate.substring(0, 10))));
				} else {
					rentalList.add(new RentalDate(Integer.parseInt(rowRentalDate.substring(11, 13)), Integer.parseInt(rowRentalDate.substring(30, 32)), LocalDate.parse(rowRentalDate.substring(0, 10))));
				}
			}

			int startTime = Integer.parseInt(rentalRequest.getStartTime());
			int endTime = Integer.parseInt(rentalRequest.getEndTime());

			if (startTime > endTime) {
				throw new InvalidParameterException("시작시각은 종료시각 보다 이전일 수 없습니다.");
			}

			for (RentalDate rentalDate : rentalList) {
				if ((startTime >= rentalDate.getStartTime() && startTime <= rentalDate.getEndTime()) || (endTime >= rentalDate.getStartTime() && endTime <= rentalDate.getEndTime()) || (startTime <= rentalDate.getStartTime() && endTime >= rentalDate.getEndTime())) {
					throw new InvalidParameterException("이미 대여중인 시간 입니다.");
				}
			}

			if (rentalRequest.getReason().length() < 6) {
				throw new InvalidParameterException("대여사유를 6글자 이상 입력해 주세요.");
			}

			if (rentalRequest.getPhoneNumber().isEmpty()) {
				throw new InvalidParameterException("신청자 연락처를 입력 하세요.");
			}

			HtmlSelect selectStartTime = (HtmlSelect) rentalPage.getElementById("fv시설대여_ddlFrTm");
			HtmlOption timeOption = selectStartTime.getOptionByValue(rentalRequest.getStartTime());
			selectStartTime.setSelectedAttribute(timeOption, true);

			HtmlSelect selectEndTime = (HtmlSelect) rentalPage.getElementById("fv시설대여_ddlToTm");
			timeOption = selectEndTime.getOptionByValue(rentalRequest.getEndTime());
			selectEndTime.setSelectedAttribute(timeOption, true);

			HtmlTextArea reason = (HtmlTextArea) rentalPage.getElementById("fv시설대여_txtRentCause");
			reason.setText(rentalRequest.getReason());

			HtmlTextArea peopleList = (HtmlTextArea) rentalPage.getElementById("fv시설대여_txtEntryList");
			peopleList.setText(rentalRequest.getPeopleList());

			HtmlInput phoneNumber = (HtmlInput) rentalPage.getElementById("fv시설대여_txtRentUserContact");
			phoneNumber.setValueAttribute(rentalRequest.getPhoneNumber());

			HtmlCheckBoxInput agreeCheck = (HtmlCheckBoxInput) rentalPage.getElementById("fv시설대여_chk동의");
			agreeCheck.setChecked(true);

			HtmlAnchor request = (HtmlAnchor) rentalPage.getElementById("fv시설대여_btnSubmit");
			rentalPage = request.click();
			Thread.sleep(3000);

			return rentalPage;
		} catch (IOException e) {
			return ExceptionUtils.rethrow(e);
		} catch (InterruptedException e) {
			return ExceptionUtils.rethrow(e);
		}
	}

}
