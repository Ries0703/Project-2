package com.javaweb.repository;

import com.javaweb.repository.entity.DistrictEntity;

public interface DistrictRepository {
	DistrictEntity getById(Long districtId);
}
