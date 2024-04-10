package com.javaweb.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.converter.BuildingConverter;
import com.javaweb.converter.BuildingSearchBuilderConverter;
import com.javaweb.dto.request.BuildingRequestDto;
import com.javaweb.dto.response.BuildingResponseDto;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.service.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService {

	@Autowired
	private BuildingRepository buildingRepository;
	@Autowired
	private BuildingConverter buildingConverter;
	@Autowired
	private BuildingSearchBuilderConverter buildingSearchBuilderConverter;

	@Override
	public List<BuildingResponseDto> findAll(Map<String, Object> params, List<String> typeCodes) {
		return buildingRepository
				.findAll(buildingSearchBuilderConverter.toBuildingSearchBuilder(params, typeCodes))
				.stream()
				.map(buildingConverter::entityToResponseDto)
				.collect(Collectors.toList());
	}

	@Override
	public void addBuilding(BuildingRequestDto buildingRequestDto) {
		buildingRepository.save(buildingConverter.requestDtoToEntity(buildingRequestDto));
	}

	@Override
	public void removeBuilding(Long[] id) {
		buildingRepository.deleteByIdIn(id);
		
	}
	
}
