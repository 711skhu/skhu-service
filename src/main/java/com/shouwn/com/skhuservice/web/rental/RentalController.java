package com.shouwn.com.skhuservice.web.rental;

import java.util.List;
import javax.servlet.http.HttpSession;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.shouwn.com.skhuservice.rental.LectureRentalInfoService;
import com.shouwn.com.skhuservice.rental.LectureRoomInfoService;
import com.shouwn.com.skhuservice.rental.RequestRentalService;
import com.shouwn.com.skhuservice.web.ApiResponse;
import com.shouwn.com.skhuservice.web.CommonResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class RentalController {
	private final LectureRoomInfoService lectureRoomInfoService;
	private final LectureRentalInfoService lectureRentalInfoService;
	private final RequestRentalService requestRentalService;

	public RentalController(LectureRoomInfoService lectureRoomInfoService, LectureRentalInfoService lectureRentalInfoService, RequestRentalService requestRentalService) {
		this.lectureRoomInfoService = lectureRoomInfoService;
		this.lectureRentalInfoService = lectureRentalInfoService;
		this.requestRentalService = requestRentalService;
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("buildings/{buildingNumber}/classrooms")
	public ApiResponse<?> getClassRoomList(@PathVariable int buildingNumber, HttpSession session) {

		HtmlPage rentalPage = (HtmlPage) session.getAttribute("rentalPage");
		List<LectureRoom> lectureRooms;

		rentalPage = lectureRoomInfoService.selectBuilding(rentalPage, buildingNumber);
		lectureRooms = lectureRoomInfoService.classRoomList(rentalPage);

		session.setAttribute("rentalPage", rentalPage);
		return CommonResponse.builder()
				.status(HttpStatus.OK)
				.message("강의실 목록 조회 성공")
				.data(lectureRooms)
				.build();
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("classrooms/{classroomNumber}/{rentalDate}/rentalList")
	public ApiResponse<?> getRentalList(@PathVariable(value = "classroomNumber") String classroomNumber, @PathVariable(value = "rentalDate") String rentalDate, HttpSession session) {

		HtmlPage rentalPage = (HtmlPage) session.getAttribute("rentalPage");
		List<LectureRentalInfo> rentalList;

		rentalPage = lectureRentalInfoService.selectClassRoomAndRentalDate(rentalPage, classroomNumber, rentalDate);
		rentalList = lectureRentalInfoService.getRentalList(rentalPage);

		session.setAttribute("rentalPage", rentalPage);
		return CommonResponse.builder()
				.status(HttpStatus.OK)
				.message("대여현황 목록 조회 성공")
				.data(rentalList)
				.build();
	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping("requestRental")
	public ApiResponse<?> requestRental(@RequestBody RentalRequest rentalRequest, HttpSession session) {

		HtmlPage rentalPage = (HtmlPage) session.getAttribute("rentalPage");

		rentalPage = requestRentalService.requestRental(rentalPage, rentalRequest);

		session.setAttribute("rentalPage", rentalPage);
		return CommonResponse.builder()
				.status(HttpStatus.OK)
				.message("대여 신청 성공")
				.build();
	}

}


