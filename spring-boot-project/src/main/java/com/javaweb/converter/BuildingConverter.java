package com.javaweb.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.dto.BuildingDTO;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.BuildingEntity;

import java.util.stream.Collectors;

@Component
public class BuildingConverter {
	@Autowired
	private DistrictRepository districtRepository;
	@Autowired
	private RentAreaRepository rentAreaRepository;
	@Autowired
	private ModelMapper modelMapper;

	public BuildingDTO entityToDto(BuildingEntity building) {
		String address = String.join(", ", building.getStreet(), building.getWard(),
				building.getDistrictEntity().getName().toString());
		String rentArea = building.getRentAreaEntities().stream().map(o -> o.getValue().toString())
				.collect(Collectors.joining(", "));

		BuildingDTO dto = modelMapper.map(building, BuildingDTO.class);
		dto.setAddress(address);
		dto.setRentAreas(rentArea);
		return dto;
	}
}
