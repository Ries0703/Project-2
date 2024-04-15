package com.javaweb.converter;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.javaweb.builder.BuildingSearch;
import com.javaweb.utils.MapUtil;

@Component
public class BuildingSearchBuilderConverter {
	public BuildingSearch toBuildingSearchBuilder(Map<String, Object> params, List<String> typeCodes) {
		return BuildingSearch.builder()
		    .name(MapUtil.getString(params.get("name")))
		    .street(MapUtil.getString(params.get("street")))
		    .ward(MapUtil.getString(params.get("ward")))
		    .districtId(MapUtil.getLong(params.get("districtId")))
		    .numberOfBasement(MapUtil.getLong(params.get("numberOfBasement")))
		    .floorArea(MapUtil.getLong(params.get("floorArea")))
		    .direction(MapUtil.getString(params.get("direction")))
		    .level(MapUtil.getString(params.get("level")))
		    .areaFrom(MapUtil.getLong(params.get("areaFrom")))
		    .areaTo(MapUtil.getLong(params.get("areaTo")))
		    .rentPriceFrom(MapUtil.getLong(params.get("rentPriceFrom")))
		    .rentPriceTo(MapUtil.getLong(params.get("rentPriceTo")))
		    .managerName(MapUtil.getString(params.get("managerName")))
		    .managerPhoneNumber(MapUtil.getString(params.get("managerPhoneNumber")))
		    .staffId(MapUtil.getLong(params.get("staffId")))
		    .typeCodes(typeCodes)
		    .build();
	}
}
