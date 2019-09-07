package com.shouwn.com.skhuservice.rental;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.shouwn.com.skhuservice.exception.InvalidParameterException;
import com.shouwn.com.skhuservice.user.UrlType;
import com.shouwn.com.skhuservice.web.rental.LectureRoom;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import org.springframework.stereotype.Service;

@Service
public class LectureRoomInfoService {

	public HtmlPage selectBuilding(HtmlPage rentalPage, int buildingNumber) {
		if (!UrlType.RENTALPAGE_URL.getUrl().equals(rentalPage.getUrl())) {
			throw new IllegalStateException("잘못된 접근 입니다.");
		}

		try {
			BuildingType type = BuildingType.valueOf(buildingNumber);
			if (type == null) {
				throw new InvalidParameterException("존재하지 않는 건물 입니다.");
			}

			for (HtmlAnchor anchor : rentalPage.getAnchors()) {
				if (StringUtils.equals(type.getBuildingButton(), anchor.getId())) {
					rentalPage = anchor.click();
					Thread.sleep(3000);
					break;
				}
			}
			return rentalPage;
		} catch (IOException e) {
			return ExceptionUtils.rethrow(e);
		} catch (InterruptedException e) {
			return ExceptionUtils.rethrow(e);
		}
	}

	public List<LectureRoom> classRoomList(HtmlPage rentalPage) {
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
			List<LectureRoom> lectureRooms = new ArrayList<>();
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
				String classroomName = lectureRoomsTable.getCellAt(i, 0).asText();
				int people = Integer.parseInt(lectureRoomsTable.getCellAt(i, 2).asText());
				ClassroomType classroomType = ClassroomType.valudOfClassroomName(classroomName);

				if (StringUtils.equals(classroomType.getClassroomName(), classroomName)) {
					lectureRooms.add(new LectureRoom(classroomType.getClassroomName(), people, classroomType.getDetailType(), classroomType.getClassType()));
				}
			}

			return lectureRooms;
		} catch (IOException e) {
			return ExceptionUtils.rethrow(e);
		} catch (InterruptedException e) {
			return ExceptionUtils.rethrow(e);
		}
	}

}
