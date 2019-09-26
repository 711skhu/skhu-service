package com.shouwn.com.skhuservice.web.user;

import java.util.List;
import javax.servlet.http.HttpSession;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.shouwn.com.skhuservice.exception.InvalidParameterException;
import com.shouwn.com.skhuservice.rental.ConnectToRentalPageService;
import com.shouwn.com.skhuservice.user.UserRentalListService;
import com.shouwn.com.skhuservice.user.UserService;
import com.shouwn.com.skhuservice.web.ApiResponse;
import com.shouwn.com.skhuservice.web.CommonResponse;
import org.apache.commons.lang3.StringUtils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
	private final UserService userService;
	private final ConnectToRentalPageService connectToRentalPageService;
	private final UserRentalListService userRentalListService;

	public UserController(UserService userService, ConnectToRentalPageService connectToRentalPageService, UserRentalListService userRentalListService) {
		this.userService = userService;
		this.connectToRentalPageService = connectToRentalPageService;
		this.userRentalListService = userRentalListService;
	}

	@PostMapping("login")
	public ApiResponse<?> login(@RequestBody UserLoginRequest loginRequest, UserInfo userInfo, HttpSession session) {

		session.setAttribute("rentalPage", null);
		HtmlPage htmlPage;
		HtmlPage nameHtmlPage;
		String userName;

		if (StringUtils.isBlank(loginRequest.getStudentNumber()) || StringUtils.isBlank(loginRequest.getPassword())) {
			throw new InvalidParameterException("아이디 혹은 비밀번호를 입력해주세요.");
		}

		htmlPage = userService.login(loginRequest);
		nameHtmlPage = connectToRentalPageService.topPage(htmlPage);
		userName = userService.userNameDisplay(nameHtmlPage, userInfo);
		htmlPage = connectToRentalPageService.connectToRentalPage(htmlPage);

		session.setAttribute("rentalPage", htmlPage);
		return CommonResponse.builder()
				.status(HttpStatus.CREATED)
				.message("로그인 성공")
				.data(userName)
				.build();
	}


	@GetMapping("rentalList")
	public ApiResponse<?> rentalList(HttpSession session) {
		HtmlPage rentalPage = (HtmlPage) session.getAttribute("rentalPage");
		List<UserLectureRentalInfo> rentalList;

		rentalList = userRentalListService.rentalList(rentalPage);

		return CommonResponse.builder()
				.status(HttpStatus.OK)
				.message("사용자 대여 목록 조회 성공")
				.data(rentalList)
				.build();
	}

	@DeleteMapping("rental/{idx}")
	public ApiResponse<?> rentalCancel(@PathVariable(value = "idx") int idx, HttpSession session) {
		HtmlPage rentalPage = (HtmlPage) session.getAttribute("rentalPage");

		userRentalListService.rentalCancel(rentalPage, idx);

		return CommonResponse.builder()
				.status(HttpStatus.OK)
				.message("사용자 대여 목록 삭제 성공")
				.build();
	}
}
