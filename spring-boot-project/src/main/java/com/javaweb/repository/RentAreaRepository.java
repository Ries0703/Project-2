package com.javaweb.repository;

import java.util.List;

public interface RentAreaRepository {
	List<String> getByBuildingId(Long buildingId);
}
