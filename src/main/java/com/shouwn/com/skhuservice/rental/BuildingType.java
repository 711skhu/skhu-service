package com.shouwn.com.skhuservice.rental;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.Getter;

@Getter
public enum BuildingType {

	SEUNGYEON_HALL(1, "승연관", "gv건물목록_ctl02_btnSelect"),
	IIMAN_HALL(2, "일만관", "gv건물목록_ctl03_btnSelect"),
	WOLDANGG_HALL(3, "월당관", "gv건물목록_ctl04_btnSelect"),
	LEECHUNHWAN_HALL(6, "이천환관", "gv건물목록_ctl07_btnSelect"),
	SSECHUNNUN_HALL(7, "새천년관", "gv건물목록_ctl08_btnSelect"),
	SUNGMIELE_HALL(9, "성미가엘성당", "gv건물목록_ctl09_btnSelect"),
	MIELE_HALL(11, "미가엘관", "gv건물목록_ctl10_btnSelect");

	private static final Map<Integer, BuildingType> map;

	static {
		map = Arrays.stream(BuildingType.values())
				.collect(Collectors.toMap(BuildingType::getBuildingNumber, b -> b));
	}

	private int buildingNumber;
	private String buildingName;
	private String buildingButton;

	BuildingType(int buildingNumber, String buildingName, String buildingButton) {
		this.buildingNumber = buildingNumber;
		this.buildingName = buildingName;
		this.buildingButton = buildingButton;
	}

	public static BuildingType valueOf(int buildingNumber) {
		return map.get(buildingNumber);
	}
}
