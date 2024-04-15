package com.javaweb.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.converter.BuildingConverter;
import com.javaweb.converter.BuildingSearchBuilderConverter;
import com.javaweb.dto.request.BuildingRequest;
import com.javaweb.dto.response.BuildingResponse;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.service.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService {
	public BuildingServiceImpl() {
		System.out.println("BuildingServiceImpl created");
	}
	@Autowired
	private BuildingRepository buildingRepository;
	@Autowired
	private BuildingConverter buildingConverter;
	@Autowired
	private BuildingSearchBuilderConverter buildingSearchBuilderConverter;

	@Override
	public List<BuildingResponse> findAll(Map<String, Object> params, List<String> typeCodes) {
		return buildingRepository.findAll(buildingSearchBuilderConverter.toBuildingSearchBuilder(params, typeCodes))
		    .stream()
		    .map(buildingConverter::entityToResponseDto)
		    .collect(Collectors.toList());
	}

	@Override
	public void addBuilding(BuildingRequest buildingRequest) {
		buildingRepository.save(buildingConverter.requestDtoToEntity(buildingRequest));
	}

	@Override
	public void removeBuilding(Long[] id) {
		buildingRepository.deleteByIdIn(id);

	}

}
