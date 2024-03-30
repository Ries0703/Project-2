package com.javaweb.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.dto.BuildingDTO;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.BuildingEntity;

@Component
public class BuildingConverter {
	@Autowired
	private DistrictRepository districtRepository;
	@Autowired
	private RentAreaRepository rentAreaRepository;
	@Autowired
	private ModelMapper modelMapper;

	public BuildingDTO entityToDto(BuildingEntity building) {
		// build address and rentAreas 
		String address = String.join(", ", building.getStreet(), building.getWard(),
				districtRepository.getById(building.getDistrictId()).getName());
		String rentAreas = String.join(", ", rentAreaRepository.getByBuildingId(building.getId()));

		BuildingDTO dto = modelMapper.map(building, BuildingDTO.class);
		dto.setAddress(address.toString());
		dto.setRentAreas(rentAreas);
		
		return dto;
	}
}
