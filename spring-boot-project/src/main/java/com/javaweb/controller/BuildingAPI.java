package com.javaweb.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.dto.request.BuildingRequestDto;
import com.javaweb.dto.response.BuildingResponseDto;
import com.javaweb.service.BuildingService;

@RestController
@RequestMapping(value = "api/building/")
public class BuildingAPI {
	@Autowired
	BuildingService buildingService;

	@GetMapping
	public List<BuildingResponseDto> getBuilding(@RequestParam Map<String, Object> params,
			@RequestParam(value = "typeCode", required = false) List<String> typeCodes) {
		return buildingService.findAll(params, typeCodes);
	}
	
	@PostMapping
	public void addBuilding(@RequestBody BuildingRequestDto buildingRequestDto) {
		buildingService.addBuilding(buildingRequestDto);
	}
	
	@DeleteMapping(value = "{id}")
	public void removeBuilding(@PathVariable Long[] id) {
		buildingService.removeBuilding(id);
	}
}