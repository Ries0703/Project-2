package com.javaweb.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.dto.BuildingDto;
import com.javaweb.service.BuildingService;

@RestController
@RequestMapping(value = "api/building/")
public class BuildingAPI {
	@Autowired
	BuildingService buildingService;

	@GetMapping
	public List<BuildingDto> getBuilding(@RequestParam Map<String, Object> params,
			@RequestParam(value = "typeCode", required = false) List<String> typeCodes) {
		return buildingService.findAll(params, typeCodes);
	}
}