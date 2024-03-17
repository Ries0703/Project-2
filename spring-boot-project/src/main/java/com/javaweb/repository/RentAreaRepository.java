package com.javaweb.repository;

import java.util.List;

import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.RentAreaEntity;

public interface RentAreaRepository {
	List<RentAreaEntity> findAll(List<BuildingEntity> buildings);
}
