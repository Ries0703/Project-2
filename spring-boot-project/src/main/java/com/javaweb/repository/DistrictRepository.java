package com.javaweb.repository;

import com.javaweb.repository.entity.DistrictEntity;

public interface DistrictRepository {
	DistrictEntity getById(long districtId);
}
