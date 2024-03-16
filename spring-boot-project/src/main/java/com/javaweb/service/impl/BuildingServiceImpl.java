package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.service.BuildingService;
import com.javaweb.service.dto.BuildingDTO;

@Service
public class BuildingServiceImpl implements BuildingService {
	@Autowired
	private BuildingRepository buildingRepository;

	@Override
	public List<BuildingDTO> findAll(Map<String, Object> params) {
		List<BuildingEntity> buildingEntities = buildingRepository.findAll(params);
		List<BuildingDTO> buildingDTOs = new ArrayList<>();
		for (BuildingEntity item : buildingEntities) {
			BuildingDTO dto = new BuildingDTO();
			dto.setName(item.getName());
			dto.setAddress(item.getStreet() + ", " + item.getWard() + ", " + item.getDistrictId());
			dto.setManagerName(item.getManagerName());
			buildingDTOs.add(dto);
		}
		return buildingDTOs;
	}

	@Override
	public List<BuildingEntity> create(List<BuildingDTO> buildingDTO) {
		return buildingRepository.save(dtoToEntity(buildingDTO));
	}

	private List<BuildingEntity> dtoToEntity(List<BuildingDTO> buildingDTOs) {
		List<BuildingEntity> converted = new ArrayList<>();
		for (BuildingDTO buildingDTO : buildingDTOs) {
			BuildingEntity insert = new BuildingEntity();
			insert.setName(buildingDTO.getName());
			String[] addressFields = buildingDTO.getAddress().split("\\s*,\\s*");
			insert.setStreet(addressFields[0]);
			insert.setWard(addressFields[1]);
			insert.setDistrictId(Long.parseLong(addressFields[2]));
			converted.add(insert);
		}
		return converted;
	}

	@Override
	public String delete(List<Long> ids) {
		return buildingRepository.delete(ids);
	}
}
