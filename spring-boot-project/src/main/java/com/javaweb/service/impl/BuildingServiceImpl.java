package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.entity.RentAreaEntity;
import com.javaweb.service.BuildingService;
import com.javaweb.service.dto.BuildingDTO;

@Service
public class BuildingServiceImpl implements BuildingService {

	@Autowired
	private BuildingRepository buildingRepository;
	@Autowired
	private DistrictRepository districtRepository;
	@Autowired
	private RentAreaRepository rentAreaRepository;

	@Override
	public List<BuildingDTO> findAll(Map<String, Object> params, List<String> typeCodes) {
		inputValidate(params);

		List<BuildingEntity> buildings = buildingRepository.findAll(params, typeCodes);
		List<DistrictEntity> districts = districtRepository.findAll(buildings);
		List<RentAreaEntity> rentAreas = rentAreaRepository.findAll(buildings);

		List<BuildingDTO> converted = new ArrayList<>();

		for (BuildingEntity b : buildings) {
			BuildingDTO dto = new BuildingDTO();
			StringBuilder address = new StringBuilder(b.getStreet() + ", " + b.getWard() + ", ");
			for (DistrictEntity de : districts) {
				if (de.getId() == b.getDistrictId()) {
					address.append(de.getName());
					break;
				}
			}
			StringBuilder areas = new StringBuilder();
			for (RentAreaEntity ra : rentAreas) {
				if (ra.getBuildingId() == b.getId()) {
					areas.append(ra.getValue()).append(", ");
				}
			}
			areas.delete(areas.length() - 2, areas.length());

			dto.setName(b.getName());
			dto.setAddress(address.toString());
			dto.setNumberOfBasement(b.getNumberOfBasement());
			dto.setManagerName(b.getManagerName());
			dto.setManagerPhoneNumber(b.getManagerPhoneNumber());
			dto.setFloorArea(b.getFloorArea());
//			dto.setUnusedArea(null);
			dto.setRentAreas(areas.toString());
			dto.setBrokageFee(b.getBrokerageFee());
			dto.setServiceFee(b.getServiceFee());
			dto.setRentPrice(b.getRentPrice());
			converted.add(dto);
		}

		return converted;
	}

	private static void inputValidate(Map<String, Object> params) {
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			switch (entry.getKey()) {
			case "floorArea":
			case "areaFrom":
			case "areaTo":
			case "rentPriceFrom":
			case "rentPriceTo":
			case "numberOfBasement":
				if (!entry.getValue().toString().trim().matches("-?\\d+(\\.\\d+)?")) {
					throw new NumberFormatException();
				}
				break;
			}
		}
	}
}
