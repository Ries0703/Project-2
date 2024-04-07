package com.javaweb.dto;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;


@Component
@Setter
@Getter
public class BuildingDTO {
	private String name;
	private String address;
	private Long numberOfBasement;
	private String managerName;
	private String managerPhoneNumber;
	private Long floorArea;
	private Long unusedArea;
	private String rentAreas;
	private Double brokageFee;
	private String serviceFee;
	private Long rentPrice;

}
