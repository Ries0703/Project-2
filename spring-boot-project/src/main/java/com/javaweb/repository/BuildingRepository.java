package com.javaweb.repository;

import java.util.List;
import java.util.Map;

public interface BuildingRepository {
	List<Map<String, Object>> findAll(Map<String, Object> params, List<String> typeCode);
}
