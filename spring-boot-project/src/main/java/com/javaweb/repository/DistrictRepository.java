package com.javaweb.repository;

import java.util.List;

import com.javaweb.repository.entity.DistrictEntity;

public interface DistrictRepository {
	List<DistrictEntity> findAll(long districtId);
}
