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
	public List<BuildingDTO> findAll(Map<String, Object> params, List<String> typeCode) {
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			switch(entry.getKey()) {
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
		
		List<Map<String, Object>> maps = buildingRepository.findAll(params, typeCode);
		List<BuildingDTO> converted = new ArrayList<>();
		for (Map<String, Object> mp : maps) {
			converted.add(mapToDto(mp));
		}
		return converted;
	}

	public BuildingDTO mapToDto(Map<String, Object> data) {
		BuildingDTO dto = new BuildingDTO();

		for (Map.Entry<String, Object> entry : data.entrySet()) {
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
				dto.setNumberOfBasement((Integer) (value));
				break;
			case "managername":
				dto.setManagerName((String) value);
				break;
			case "managerphonenumber":
				dto.setManagerPhoneNumber((String) value);
				break;
			case "floorarea":
				dto.setFloorArea((Integer) (value));
				break;
			case "rentareas":
				dto.setRentAreas((String) value);
				break;
			case "brokagefee":
				dto.setBrokageFee((Double) (value));
				break;
			case "servicefee":
				dto.setServiceFee((String) value);
				break;
			case "rentprice":
				dto.setRentPrice((Integer) (value));
				break;
			}
		}
		return dto;
	}
}
