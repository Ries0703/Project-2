package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.service.BuildingService;
import com.javaweb.service.dto.BuildingDTO;

@Service
public class BuildingServiceImpl implements BuildingService {

	@Autowired
	private BuildingRepository buildingRepository;

	@Override
	public List<BuildingDTO> findAll(Map<String, Object> params) {
		List<Map<String, Object>> maps = buildingRepository.findAll(params);
		List<BuildingDTO> converted = new ArrayList<>();
		for (Map<String, Object> mp : maps) {
			converted.add(mapToDto(mp));
		}
		return converted;
	}

	public BuildingDTO mapToDto(Map<String, Object> data) {
		BuildingDTO dto = new BuildingDTO();

		for (Map.Entry<String, Object> entry : data.entrySet()) {
			if (entry.getValue() == null) {
				continue;
			}

			String key = entry.getKey();
			Object value = entry.getValue();

			switch (key) {
			case "name":
				dto.setName((String) value);
				break;
			case "address":
				dto.setAddress((String) value);
				break;
			case "numberofbasement":
				dto.setNumberOfBasement(Integer.valueOf(value.toString()));
				break;
			case "managername":
				dto.setManagerName((String) value);
				break;
			case "managerphonenumber":
				dto.setManagerPhoneNumber((String) value);
				break;
			case "floorarea":
				dto.setFloorArea(Integer.valueOf(value.toString()));
				break;
			case "rentareas":
				dto.setRentAreas((String) value);
				break;
			case "brokagefee":
				dto.setBrokageFee(Double.valueOf(value.toString()));
				break;
			case "servicefee":
				dto.setServiceFee(String.valueOf(value.toString()));
				break;
			case "rentprice":
				dto.setRentPrice(Integer.valueOf(value.toString()));
				break;
			}
		}
		return dto;
	}
}
