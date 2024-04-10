package com.javaweb.dto;

import lombok.Getter;
import lombok.Setter;



@Setter
@Getter
public class BuildingDto {
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
