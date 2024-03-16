package com.javaweb.service;

import java.util.List;
import java.util.Map;

import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.service.dto.BuildingDTO;

public interface BuildingService {
	List<BuildingDTO> findAll(Map<String, Object> params);
	List<BuildingEntity> create(List<BuildingDTO> buildingDTO);
	String delete(List<Long> ids);
}
