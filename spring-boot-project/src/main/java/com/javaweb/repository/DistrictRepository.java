package com.javaweb.repository;

import java.util.List;

import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;

public interface DistrictRepository {
	List<DistrictEntity> findAll(List<BuildingEntity> buildings);
}
