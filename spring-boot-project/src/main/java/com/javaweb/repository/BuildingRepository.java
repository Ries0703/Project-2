package com.javaweb.repository;

import java.util.List;
import java.util.Map;


import com.javaweb.repository.entity.BuildingEntity;

public interface BuildingRepository {
	List<BuildingEntity> findAll(Map<String, Object> params);
	List<BuildingEntity> save(List<BuildingEntity> buildingEntities);
	String delete(List<Long> ids);
}
