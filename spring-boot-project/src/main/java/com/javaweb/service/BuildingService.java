package com.javaweb.service;

import java.util.List;
import java.util.Map;

import com.javaweb.service.dto.BuildingDTO;

public interface BuildingService {
	List<BuildingDTO> findAll(Map<String, Object> params);
}
