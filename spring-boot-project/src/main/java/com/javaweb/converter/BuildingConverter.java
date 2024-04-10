package com.javaweb.converter;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.dto.BuildingDto;
import com.javaweb.repository.entity.BuildingEntity;

@Component
public class BuildingConverter {
	
	@Autowired
	private ModelMapper modelMapper;

	public BuildingDto entityToDto(BuildingEntity building) {
		String address = String.join(", ", building.getStreet(), building.getWard(),
				building.getDistrictEntity().getName());
		String rentArea = building.getRentAreaEntities()
									.stream()
									.map(o -> o.getValue().toString())
									.collect(Collectors.joining(", "));

		BuildingDto dto = modelMapper.map(building, BuildingDto.class);
		dto.setAddress(address);
		dto.setRentAreas(rentArea);
		return dto;
	}
}
