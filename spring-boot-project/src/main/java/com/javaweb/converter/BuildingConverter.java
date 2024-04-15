package com.javaweb.converter;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.dto.request.BuildingRequest;
import com.javaweb.dto.response.BuildingResponse;
import com.javaweb.repository.entity.BuildingEntity;

@Component
public class BuildingConverter {

	@Autowired
	private ModelMapper modelMapper;

	public BuildingResponse entityToResponseDto(BuildingEntity building) {
		String address = String.join(", ", building.getStreet(), building.getWard(), building.getDistrictEntity().getName());
		String rentArea = building.getRentAreaEntities()
		    .stream()
		    .map(o -> o.getValue().toString())
		    .collect(Collectors.joining(", "));
		BuildingResponse dto = modelMapper.map(building, BuildingResponse.class);
		dto.setAddress(address);
		dto.setRentAreas(rentArea);
		return dto;
	}

	public BuildingEntity requestDtoToEntity(BuildingRequest buildingRequest) {
		return modelMapper.map(buildingRequest, BuildingEntity.class);
	}
}
