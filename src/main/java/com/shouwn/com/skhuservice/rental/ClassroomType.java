package com.shouwn.com.skhuservice.rental;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.Getter;

@Getter
public enum ClassroomType {

	SEUNGYEONHALL_1406("1406", "gv시설목록_ctl02_btnSelect", DetailType.PROJECTOR, RoomType.SPECIAL),
	SEUNGYEONHALL_1407("1407", "gv시설목록_ctl03_btnSelect", DetailType.PROJECTOR, RoomType.SPECIAL),
	SEUNGYEONHALL_1501("1501", "gv시설목록_ctl04_btnSelect", DetailType.PROJECTOR, RoomType.SMALL),
	SEUNGYEONHALL_1502("1502", "gv시설목록_ctl05_btnSelect", DetailType.PROJECTOR, RoomType.SMALL),
	SEUNGYEONHALL_1503("1503", "gv시설목록_ctl06_btnSelect", DetailType.PROJECTOR, RoomType.MIDDLE),
	SEUNGYEONHALL_1504("1504", "gv시설목록_ctl07_btnSelect", DetailType.PROJECTOR, RoomType.SMALL),
	SEUNGYEONHALL_1505("1505", "gv시설목록_ctl08_btnSelect", DetailType.PROJECTOR, RoomType.MIDDLE),
	SEUNGYEONHALL_1506("1506", "gv시설목록_ctl09_btnSelect", DetailType.PROJECTOR, RoomType.SMALL),
	SEUNGYEONHALL_1507("1507", "gv시설목록_ctl10_btnSelect", DetailType.PROJECTOR, RoomType.SMALL),
	SEUNGYEONHALL_1508("1508", "gv시설목록_ctl11_btnSelect", DetailType.PROJECTOR, RoomType.SMALL),

	IIMANHALL_B103("B103", "gv시설목록_ctl02_btnSelect", DetailType.NULL, RoomType.SPECIAL),
	IIMANHALL_B104("B104", "gv시설목록_ctl03_btnSelect", DetailType.NULL, RoomType.SPECIAL),
	IIMANHALL_B105("B105", "gv시설목록_ctl04_btnSelect", DetailType.PROJECTOR, RoomType.SPECIAL),
	IIMANHALL_B2101("B2101", "gv시설목록_ctl05_btnSelect", DetailType.PROJECTOR, RoomType.SPECIAL),
	IIMANHALL_B2102("B2102", "gv시설목록_ctl06_btnSelect", DetailType.PROJECTOR, RoomType.SPECIAL),
	IIMANHALL_2401("2401", "gv시설목록_ctl07_btnSelect", DetailType.PROJECTOR, RoomType.BIG),
	IIMANHALL_2402("2402", "gv시설목록_ctl08_btnSelect", DetailType.PROJECTOR, RoomType.BIG),

	WOLDANGGHALL_3301("3301", "gv시설목록_ctl02_btnSelect", DetailType.PROJECTOR, RoomType.BIG),
	WOLDANGGHALL_3302("3302", "gv시설목록_ctl03_btnSelect", DetailType.PROJECTOR, RoomType.BIG),
	WOLDANGGHALL_3501("3501", "gv시설목록_ctl04_btnSelect", DetailType.PROJECTOR, RoomType.SMALL),
	WOLDANGGHALL_3502("3502", "gv시설목록_ctl05_btnSelect", DetailType.PROJECTOR, RoomType.SMALL),
	WOLDANGGHALL_3503("3503", "gv시설목록_ctl06_btnSelect", DetailType.PROJECTOR, RoomType.SMALL),
	WOLDANGGHALL_3504("3504", "gv시설목록_ctl07_btnSelect", DetailType.PROJECTOR, RoomType.SMALL),

	LEECHUNHWANHALL_6110("6110", "gv시설목록_ctl02_btnSelect", DetailType.PROJECTOR, RoomType.SPECIAL),
	LEECHUNHWANHALL_6500("6500", "gv시설목록_ctl03_btnSelect", DetailType.PROJECTOR, RoomType.SPECIAL),

	SSECHUNNUNHALL_7104("7104", "gv시설목록_ctl02_btnSelect", DetailType.COMPUTER, RoomType.SPECIAL),
	SSECHUNNUNHALL_7201("7201", "gv시설목록_ctl03_btnSelect", DetailType.PROJECTOR, RoomType.SMALL),
	SSECHUNNUNHALL_7202("7202", "gv시설목록_ctl04_btnSelect", DetailType.NULL, RoomType.MIDDLE),
	SSECHUNNUNHALL_7204("7204", "gv시설목록_ctl05_btnSelect", DetailType.PROJECTOR, RoomType.MIDDLE),
	SSECHUNNUNHALL_7205("7205", "gv시설목록_ctl06_btnSelect", DetailType.PROJECTOR, RoomType.MIDDLE),
	SSECHUNNUNHALL_7206("7206", "gv시설목록_ctl07_btnSelect", DetailType.PROJECTOR, RoomType.MIDDLE),
	SSECHUNNUNHALL_7207("7207", "gv시설목록_ctl08_btnSelect", DetailType.PROJECTOR, RoomType.BIG),
	SSECHUNNUNHALL_7208("7208", "gv시설목록_ctl09_btnSelect", DetailType.PROJECTOR, RoomType.BIG),
	SSECHUNNUNHALL_7301("7301", "gv시설목록_ctl10_btnSelect", DetailType.NULL, RoomType.SMALL),
	SSECHUNNUNHALL_7302("7302", "gv시설목록_ctl11_btnSelect", DetailType.NULL, RoomType.SMALL),
	SSECHUNNUNHALL_7303("7303", "gv시설목록_ctl02_btnSelect", DetailType.NULL, RoomType.SMALL),
	SSECHUNNUNHALL_7304("7304", "gv시설목록_ctl03_btnSelect", DetailType.NULL, RoomType.MIDDLE),
	SSECHUNNUNHALL_7305("7305", "gv시설목록_ctl04_btnSelect", DetailType.NULL, RoomType.SMALL),
	SSECHUNNUNHALL_7306("7306", "gv시설목록_ctl05_btnSelect", DetailType.NULL, RoomType.SMALL),
	SSECHUNNUNHALL_7307("7307", "gv시설목록_ctl06_btnSelect", DetailType.NULL, RoomType.SMALL),
	SSECHUNNUNHALL_7308("7308", "gv시설목록_ctl07_btnSelect", DetailType.PROJECTOR, RoomType.BIG),
	SSECHUNNUNHALL_7309("7309", "gv시설목록_ctl08_btnSelect", DetailType.PROJECTOR, RoomType.BIG),

	SUNGMIELEHALL_9101("9101", "gv시설목록_ctl02_btnSelect", DetailType.PROJECTOR, RoomType.SPECIAL),

	MIELEHALL_M201("M201", "gv시설목록_ctl02_btnSelect", DetailType.PROJECTOR, RoomType.SMALL),
	MIELEHALL_M202("M202", "gv시설목록_ctl03_btnSelect", DetailType.PROJECTOR, RoomType.SMALL),
	MIELEHALL_M203("M203", "gv시설목록_ctl04_btnSelect", DetailType.PROJECTOR, RoomType.SMALL),
	MIELEHALL_M204("M204", "gv시설목록_ctl05_btnSelect", DetailType.BOTH, RoomType.SMALL),
	MIELEHALL_M205("M205", "gv시설목록_ctl06_btnSelect", DetailType.PROJECTOR, RoomType.BIG),
	MIELEHALL_M301("M301", "gv시설목록_ctl07_btnSelect", DetailType.PROJECTOR, RoomType.BIG),
	MIELEHALL_M401("M401", "gv시설목록_ctl08_btnSelect", DetailType.PROJECTOR, RoomType.SPECIAL),
	MIELEHALL_M402("M402", "gv시설목록_ctl09_btnSelect", DetailType.PROJECTOR, RoomType.MIDDLE),
	MIELEHALL_M403("M403", "gv시설목록_ctl10_btnSelect", DetailType.PROJECTOR, RoomType.SMALL),
	MIELEHALL_M404("M404", "gv시설목록_ctl11_btnSelect", DetailType.PROJECTOR, RoomType.SMALL),
	MIELEHALL_M406("M406", "gv시설목록_ctl02_btnSelect", DetailType.PROJECTOR, RoomType.MIDDLE),
	MIELEHALL_M407("M407", "gv시설목록_ctl03_btnSelect", DetailType.PROJECTOR, RoomType.MIDDLE);

	private static final Map<String, ClassroomType> map;

	static {
		map = Arrays.stream(ClassroomType.values())
				.collect(Collectors.toMap(ClassroomType::getClassroomName, b -> b));
	}

	private String classroomName;
	private String button;
	private DetailType detailType;
	private RoomType classType;

	ClassroomType(String classroomName, String button, DetailType detailType, RoomType classType) {
		this.classroomName = classroomName;
		this.button = button;
		this.detailType = detailType;
		this.classType = classType;
	}

	public static ClassroomType valudOfClassroomName(String classroomName) {
		return map.get(classroomName);
	}
}
