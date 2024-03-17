package com.javaweb.service.dto;

import org.springframework.stereotype.Component;

@Component
public class BuildingDTO {
	private String name;
	private String address;
	private Integer numberOfBasement;
	private String managerName;
	private String managerPhoneNumber;
	private Integer floorArea;
	private Integer unusedArea;
	private String rentAreas;
	private Double brokageFee;
	private String serviceFee;
	private Integer rentPrice;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getNumberOfBasement() {
		return numberOfBasement;
	}

	public void setNumberOfBasement(Integer numberOfBasement) {
		this.numberOfBasement = numberOfBasement;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerPhoneNumber() {
		return managerPhoneNumber;
	}

	public void setManagerPhoneNumber(String managerPhoneNumber) {
		this.managerPhoneNumber = managerPhoneNumber;
	}

	public Integer getFloorArea() {
		return floorArea;
	}

	public void setFloorArea(Integer floorArea) {
		this.floorArea = floorArea;
	}

	public Integer getUnusedArea() {
		return unusedArea;
	}

	public void setUnusedArea(Integer unusedArea) {
		this.unusedArea = unusedArea;
	}

	public String getRentAreas() {
		return rentAreas;
	}

	public void setRentAreas(String rentAreas) {
		this.rentAreas = rentAreas;
	}

	public Double getBrokageFee() {
		return brokageFee;
	}

	public void setBrokageFee(Double brokageFee) {
		this.brokageFee = brokageFee;
	}

	public String getServiceFee() {
		return serviceFee;
	}

	public void setServiceFee(String serviceFee) {
		this.serviceFee = serviceFee;
	}

	public Integer getRentPrice() {
		return rentPrice;
	}

	public void setRentPrice(Integer rentPrice) {
		this.rentPrice = rentPrice;
	}

}
