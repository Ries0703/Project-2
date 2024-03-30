package com.javaweb.converter;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.utils.MapUtil;

@Component
public class BuildingSearchBuilderConverter {
	public BuildingSearchBuilder toBuildingSearchBuilder(Map<String, Object> params, List<String> typeCodes) {
		return new BuildingSearchBuilder
				.Builder()
				.setName(MapUtil.getString(params.get("name")))
				.setStreet(MapUtil.getString(params.get("street")))
				.setWard(MapUtil.getString(params.get("ward")))
				.setDistrictId(MapUtil.getLong(params.get("districtId")))
				.setNumberOfBasement(MapUtil.getLong(params.get("numberOfBasement")))
				.setFloorArea(MapUtil.getLong(params.get("floorArea")))
				.setDirection(MapUtil.getString(params.get("direction")))
				.setLevel(MapUtil.getString(params.get("level")))
				.setAreaFrom(MapUtil.getLong(params.get("areaFrom")))
				.setAreaTo(MapUtil.getLong(params.get("areaTo")))
				.setRentPriceFrom(MapUtil.getLong(params.get("rentPriceFrom")))
				.setRentPriceTo(MapUtil.getLong(params.get("rentPriceTo")))
				.setManagerName(MapUtil.getString(params.get("managerName")))
				.setManagerPhoneNumber(MapUtil.getString(params.get("managerPhoneNumber")))
				.setStaffId(MapUtil.getLong(params.get("staffId")))
				.setTypeCodes(typeCodes)
				.build();
	}
}
