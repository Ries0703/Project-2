package com.javaweb.service;

import java.util.List;
import java.util.Map;

import com.javaweb.dto.BuildingDto;

public interface BuildingService {
	List<BuildingDto> findAll(Map<String, Object> params, List<String> typeCodes);
}
