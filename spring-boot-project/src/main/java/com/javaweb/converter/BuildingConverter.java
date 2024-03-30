package com.javaweb.converter;

import java.util.List;

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

	public BuildingDTO entityToDto(BuildingEntity building) {
		BuildingDTO dto = new BuildingDTO();
		String address = new StringBuilder().append(building.getStreet()).append(", ").append(building.getWard())
				.append(", ").append(districtRepository.getById(building.getDistrictId()).getName()).toString();
		List<String> areas = rentAreaRepository.getByBuildingId(building.getId());
		dto.setName(building.getName());
		dto.setAddress(address.toString());
		dto.setNumberOfBasement(building.getNumberOfBasement());
		dto.setManagerName(building.getManagerName());
		dto.setManagerPhoneNumber(building.getManagerPhoneNumber());
		dto.setFloorArea(building.getFloorArea());
		dto.setUnusedArea(null);
		dto.setRentAreas(String.join(", ", areas));
		dto.setBrokageFee(building.getBrokerageFee());
		dto.setServiceFee(building.getServiceFee());
		dto.setRentPrice(building.getRentPrice());
		return dto;
	}
}
