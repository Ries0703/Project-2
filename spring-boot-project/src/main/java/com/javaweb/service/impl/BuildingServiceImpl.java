package com.javaweb.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.converter.BuildingConverter;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.service.BuildingService;
import com.javaweb.service.dto.BuildingDTO;

@Service
public class BuildingServiceImpl implements BuildingService {

	@Autowired
	private BuildingRepository buildingRepository;
	@Autowired
	private BuildingConverter buildingConverter;
	@Override
	public List<BuildingDTO> findAll(Map<String, Object> params, List<String> typeCodes) {
		return buildingRepository.findAll(params, typeCodes).stream()
				.map(building -> buildingConverter.entityToDto(building)).collect(Collectors.toList());
	}
}
