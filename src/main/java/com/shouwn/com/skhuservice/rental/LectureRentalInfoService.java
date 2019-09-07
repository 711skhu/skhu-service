package com.shouwn.com.skhuservice.rental;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.shouwn.com.skhuservice.exception.InvalidParameterException;
import com.shouwn.com.skhuservice.user.UrlType;
import com.shouwn.com.skhuservice.web.rental.LectureRentalInfo;
import com.shouwn.com.skhuservice.web.rental.RentalDate;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import org.springframework.stereotype.Service;

@Service
public class LectureRentalInfoService {

	public HtmlPage selectClassRoomAndRentalDate(HtmlPage rentalPage, String classroomNumber, String rentalDate) {
		if (!UrlType.RENTALPAGE_URL.getUrl().equals(rentalPage.getUrl())) {
			throw new IllegalStateException("잘못된 접근 입니다.");
		}
		if (!rentalDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
			throw new InvalidParameterException("잘못된 날짜 형식 입니다.");
		}

		try {
			int pageCount = 1;
			for (HtmlAnchor anchor : rentalPage.getAnchors()) {
				if (StringUtils.equals(("javascript:__doPostBack('gv시설목록','Page$" + pageCount + "')"), anchor.getHrefAttribute())) {
					rentalPage = anchor.click();
					Thread.sleep(3000);
					break;
				}
			}

			HtmlTable lectureRoomsTable = (HtmlTable) rentalPage.getElementById("gv시설목록");
			ClassroomType type = null;
			for (int i = 1; i < lectureRoomsTable.getRowCount(); i++) {
				if (lectureRoomsTable.getRow(i).getChildElementCount() == 1) {
					if (lectureRoomsTable.getRow(i).getTextContent().contains(Integer.toString(pageCount + 1))) {
						HtmlAnchor pagination = rentalPage.getAnchorByHref("javascript:__doPostBack('gv시설목록','Page$" + (++pageCount) + "')");
						rentalPage = pagination.click();
						Thread.sleep(3000);
						lectureRoomsTable = (HtmlTable) rentalPage.getElementById("gv시설목록");
						i = 0;
						continue;
					} else {
						break;
					}
				}

				if (lectureRoomsTable.getCellAt(i, 0).asText().equals(classroomNumber)) {
					type = ClassroomType.valudOfClassroomName(classroomNumber);
					break;
				}
			}

			if (type == null) {
				throw new InvalidParameterException("존재하지 않는 강의실 입니다.");
			}

			HtmlAnchor classroomAnchor = rentalPage.getAnchorByHref("javascript:__doPostBack('" + type.getButton().replace('_', '$') + "','')");
			rentalPage = classroomAnchor.click();
			Thread.sleep(3000);

			HtmlInput rentalDateInput = (HtmlInput) rentalPage.getElementById("txtRentDt");
			rentalDateInput.setValueAttribute(rentalDate);

			HtmlInput inquiryButton = (HtmlInput) rentalPage.getElementById("btnList");
			rentalPage = inquiryButton.click();
			Thread.sleep(3000);

			return rentalPage;
		} catch (IOException e) {
			return ExceptionUtils.rethrow(e);
		} catch (InterruptedException e) {
			return ExceptionUtils.rethrow(e);
		}
	}

	public List<LectureRentalInfo> getRentalList(HtmlPage rentalPage) {
		try {
			int pageCount = 1;
			for (HtmlAnchor anchor : rentalPage.getAnchors()) {
				if (StringUtils.equals(("javascript:__doPostBack('gv시설대여현황','Page$" + pageCount + "')"), anchor.getHrefAttribute())) {
					rentalPage = anchor.click();
					Thread.sleep(3000);
					break;
				}
			}

			List<LectureRentalInfo> rentalList = new ArrayList<>();
			HtmlTable rentalListTable = (HtmlTable) rentalPage.getElementById("gv시설대여현황");
			int index = 0;
			for (int i = 1; i < rentalListTable.getRowCount(); i++) {
				if (rentalListTable.getRow(i).getChildElementCount() == 1) {
					if (rentalListTable.getRow(i).getTextContent().contains(Integer.toString(pageCount + 1))) {
						HtmlAnchor pagination = rentalPage.getAnchorByHref("javascript:__doPostBack('gv시설대여현황','Page$" + (++pageCount) + "')");
						rentalPage = pagination.click();
						Thread.sleep(3000);
						rentalListTable = (HtmlTable) rentalPage.getElementById("gv시설대여현황");
						i = 0;
						continue;
					} else {
						break;
					}
				}

				if (!rentalListTable.getCellAt(i, 2).asText().equals("제한")) {
					String rentalState = rentalListTable.getCellAt(i, 0).asText();
					String rowRentalDate = rentalListTable.getCellAt(i, 1).asText();

					RentalDate rentalDate = new RentalDate(Integer.parseInt(rowRentalDate.substring(11, 13)), Integer.parseInt(rowRentalDate.substring(30, 32)) + 1, LocalDate.parse(rowRentalDate.substring(0, 10)));

					rentalList.add(new LectureRentalInfo(++index, rentalState, rentalDate));
				}
			}

			return rentalList;
		} catch (IOException e) {
			return ExceptionUtils.rethrow(e);
		} catch (InterruptedException e) {
			return ExceptionUtils.rethrow(e);
		}
	}

}
