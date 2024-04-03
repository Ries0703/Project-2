package com.javaweb.builder;

import java.util.List;

public class BuildingSearchBuilder {
	private final String name;
	private String street;
	private final String ward;
	private final Long districtId;
	private final Long numberOfBasement;
	private final Long floorArea;
	private final String direction;
	private final String level;
	private final Long areaFrom;
	private final Long areaTo;
	private final Long rentPriceFrom;
	private final Long rentPriceTo;
	private final String managerName;
	private final String managerPhoneNumber;
	private final Long staffId;
	private final List<String> typeCodes;

	
	public BuildingSearchBuilder(Builder builder) {
		this.name = builder.name;
		this.street = builder.street;
		this.ward = builder.ward;
		this.districtId = builder.districtId;
		this.numberOfBasement = builder.numberOfBasement;
		this.floorArea = builder.floorArea;
		this.direction = builder.direction;
		this.level = builder.level;
		this.areaFrom = builder.areaFrom;
		this.areaTo = builder.areaTo;
		this.rentPriceFrom = builder.rentPriceFrom;
		this.rentPriceTo = builder.rentPriceTo;
		this.managerName = builder.managerName;
		this.managerPhoneNumber = builder.managerPhoneNumber;
		this.staffId = builder.staffId;
		this.typeCodes = builder.typeCodes;
	}

	public static class Builder {
		private String name;
		private String street;
		private String ward;
		private Long districtId;
		private Long numberOfBasement;
		private Long floorArea;
		private String direction;
		private String level;
		private Long areaFrom;
		private Long areaTo;
		private Long rentPriceFrom;
		private Long rentPriceTo;
		private String managerName;
		private String managerPhoneNumber;
		private Long staffId;
		private List<String> typeCodes;

		public BuildingSearchBuilder build() {
			return new BuildingSearchBuilder(this);
		}
		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setStreet(String street) {
			this.street = street;
			return this;
		}

		public Builder setWard(String ward) {
			this.ward = ward;
			return this;
		}

		public Builder setDistrictId(Long districtId) {
			this.districtId = districtId;
			return this;
		}

		public Builder setNumberOfBasement(Long numberOfBasement) {
			this.numberOfBasement = numberOfBasement;
			return this;
		}

		public Builder setFloorArea(Long floorArea) {
			this.floorArea = floorArea;
			return this;
		}

		public Builder setDirection(String direction) {
			this.direction = direction;
			return this;
		}

		public Builder setLevel(String level) {
			this.level = level;
			return this;
		}

		public Builder setAreaFrom(Long areaFrom) {
			this.areaFrom = areaFrom;
			return this;
		}

		public Builder setAreaTo(Long areaTo) {
			this.areaTo = areaTo;
			return this;
		}

		public Builder setRentPriceFrom(Long rentPriceFrom) {
			this.rentPriceFrom = rentPriceFrom;
			return this;
		}

		public Builder setRentPriceTo(Long rentPriceTo) {
			this.rentPriceTo = rentPriceTo;
			return this;
		}

		public Builder setManagerName(String managerName) {
			this.managerName = managerName;
			return this;
		}

		public Builder setManagerPhoneNumber(String managerPhoneNumber) {
			this.managerPhoneNumber = managerPhoneNumber;
			return this;
		}

		public Builder setStaffId(Long staffId) {
			this.staffId = staffId;
			return this;
		}

		public Builder setTypeCodes(List<String> typeCodes) {
			this.typeCodes = typeCodes;
			return this;
		}
	}

	public String getName() {
		return name;
	}

	public String getStreet() {
		return street;
	}

	public String getWard() {
		return ward;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public Long getNumberOfBasement() {
		return numberOfBasement;
	}

	public Long getFloorArea() {
		return floorArea;
	}

	public String getDirection() {
		return direction;
	}

	public String getLevel() {
		return level;
	}

	public Long getAreaFrom() {
		return areaFrom;
	}

	public Long getAreaTo() {
		return areaTo;
	}

	public Long getRentPriceFrom() {
		return rentPriceFrom;
	}

	public Long getRentPriceTo() {
		return rentPriceTo;
	}

	public String getManagerName() {
		return managerName;
	}

	public String getManagerPhoneNumber() {
		return managerPhoneNumber;
	}

	public Long getStaffId() {
		return staffId;
	}

	public List<String> getTypeCodes() {
		return typeCodes;
	}



}
