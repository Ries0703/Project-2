package com.javaweb.builder;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class BuildingSearch {
	private final String name;
	private String street;
	private final String ward;
	private final Long districtId;
	private final Long numberOfBasement;
	private final Long floorArea;
	private final String direction;
	private final String level;
	private final Long areaFrom;
	private final Long areaTo;
	private final Long rentPriceFrom;
	private final Long rentPriceTo;
	private final String managerName;
	private final String managerPhoneNumber;
	private final Long staffId;
	private final List<String> typeCodes;
}
