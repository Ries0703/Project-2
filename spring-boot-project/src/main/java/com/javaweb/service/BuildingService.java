package com.javaweb.service;

import java.util.List;
import java.util.Map;

import com.javaweb.dto.request.BuildingRequestDto;
import com.javaweb.dto.response.BuildingResponseDto;

public interface BuildingService {
	List<BuildingResponseDto> findAll(Map<String, Object> params, List<String> typeCodes);
	void addBuilding(BuildingRequestDto buildingRequestDto);
	void removeBuilding(Long[] id);
}
