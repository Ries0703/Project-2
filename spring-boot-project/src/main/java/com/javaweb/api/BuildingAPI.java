package com.javaweb.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.service.BuildingService;
import com.javaweb.service.dto.BuildingDTO;

@RestController
@RequestMapping(value = "api/building/")
public class BuildingAPI {
	@Autowired
	BuildingService buildingService;

	@GetMapping
	public List<BuildingDTO> getBuilding(@RequestParam Map<String, Object> params) {
		List<BuildingDTO> results = buildingService.findAll(params);
		return results;
	}

	@PostMapping
	public ResponseEntity<?> createBuilding(@RequestBody List<BuildingDTO> buildingDTO) {
		List<BuildingEntity> created = buildingService.create(buildingDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteBuilding(@PathVariable List<Long> ids) {
		
		
		return null;
	}
	
}