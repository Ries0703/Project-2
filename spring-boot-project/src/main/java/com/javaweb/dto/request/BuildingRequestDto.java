package com.javaweb.dto.request;

import com.javaweb.repository.entity.DistrictEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuildingRequestDto {
	private Long id;
	private String name;
	private String street;
	private String ward;
	private DistrictEntity districtEntity;
	private Long rentPrice;
}
