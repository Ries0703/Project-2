package com.javaweb.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.service.dto.BuildingDTO;

@Component
public class BuildingConverter {
	@Autowired
	private DistrictRepository districtRepository;
	@Autowired
	private RentAreaRepository rentAreaRepository;
	@Autowired
	private ModelMapper modelMapper;

	public BuildingDTO entityToDto(BuildingEntity buildingEntity) {
		// build address and rentAreas 
		String address = String.join(", ", buildingEntity.getStreet(), buildingEntity.getWard(),
				districtRepository.getById(buildingEntity.getDistrictId()).getName());
		String rentAreas = String.join(", ", rentAreaRepository.getByBuildingId(buildingEntity.getId()));

		BuildingDTO dto = modelMapper.map(buildingEntity, BuildingDTO.class);
		dto.setAddress(address.toString());
		dto.setRentAreas(rentAreas);
		
		return dto;
	}
}
