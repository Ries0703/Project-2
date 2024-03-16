package com.javaweb.repository.entity;

public class BuildingRentTypeEntity {
	private long id;
	private long buildingId;
	private long rentTypeId;

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}

	public Long getRentTypeId() {
		return rentTypeId;
	}

	public void setRentTypeId(Long rentTypeId) {
		this.rentTypeId = rentTypeId;
	}

}
