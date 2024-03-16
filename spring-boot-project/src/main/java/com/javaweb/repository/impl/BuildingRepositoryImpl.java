package com.javaweb.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {

	@Override
	public List<BuildingEntity> findAll(Map<String, Object> params) {
		return null;
	}

	@Override
	public List<BuildingEntity> save(List<BuildingEntity> buildingEntity) {
		return null;
	}

	@Override
	public String delete(List<Long> ids) {
		return null;
	}



}
