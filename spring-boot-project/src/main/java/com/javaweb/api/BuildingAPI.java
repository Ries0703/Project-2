package com.javaweb.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.service.BuildingService;
import com.javaweb.service.dto.BuildingDTO;

@RestController
@RequestMapping(value = "api/buildings/")
public class BuildingAPI {
	@Autowired
	BuildingService buildingService;

	@GetMapping
	public List<BuildingDTO> getBuilding(@RequestParam Map<String, Object> params,
			@RequestParam(value = "typeCode", required = false) List<String> typeCodes) {
		List<BuildingDTO> results = buildingService.findAll(params, typeCodes);
		return results;
	}
}