package com.javaweb.service;

import java.util.List;
import java.util.Map;

import com.javaweb.dto.request.BuildingRequest;
import com.javaweb.dto.response.BuildingResponse;

public interface BuildingService {
	List<BuildingResponse> findAll(Map<String, Object> params, List<String> typeCodes);
	void addBuilding(BuildingRequest buildingRequest);
	void removeBuilding(Long[] id);
}
