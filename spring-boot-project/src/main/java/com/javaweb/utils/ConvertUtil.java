package com.javaweb.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.entity.RentAreaEntity;
import com.javaweb.service.dto.BuildingDTO;

public class ConvertUtil {
	public static BuildingDTO entityToDto(BuildingEntity b, DistrictRepository districtRepository,
			RentAreaRepository rentAreaRepository) {
		BuildingDTO dto = new BuildingDTO();
		String address = b.getStreet() + ", " + b.getWard() + ", "
				+ districtRepository.getById(b.getDistrictId()).getName();
		String areas = rentAreaRepository.getByBuildingId(b.getId());
		dto.setName(b.getName());
		dto.setAddress(address.toString());
		dto.setNumberOfBasement(b.getNumberOfBasement());
		dto.setManagerName(b.getManagerName());
		dto.setManagerPhoneNumber(b.getManagerPhoneNumber());
		dto.setFloorArea(b.getFloorArea());
		dto.setUnusedArea(null);
		dto.setRentAreas(areas.toString());
		dto.setBrokageFee(b.getBrokerageFee());
		dto.setServiceFee(b.getServiceFee());
		dto.setRentPrice(b.getRentPrice());
		return dto;
	}

	public static List<BuildingEntity> resultSetToBuildingEntities(ResultSet rs) throws SQLException {
		List<BuildingEntity> results = new ArrayList<>();
		while (rs.next()) {
			BuildingEntity building = new BuildingEntity();
			building.setId(rs.getLong("id"));
			building.setName(rs.getString("name"));
			building.setStreet(rs.getString("street"));
			building.setWard(rs.getString("ward"));
			building.setDistrictId(rs.getLong("districtid"));
			building.setNumberOfBasement(rs.getInt("numberofbasement"));
			building.setManagerName(rs.getString("managername"));
			building.setManagerPhoneNumber(rs.getString("managerphonenumber"));
			building.setFloorArea(rs.getInt("floorarea"));
			building.setBrokerageFee(rs.getDouble("brokeragefee"));
			building.setServiceFee(rs.getString("servicefee"));
			building.setRentPrice(rs.getInt("rentprice"));
			results.add(building);
		}
		return results;
	}

	public static List<DistrictEntity> resultSetToDistrictEntities(ResultSet rs) throws SQLException {
		List<DistrictEntity> results = new ArrayList<>();
		while (rs.next()) {
			DistrictEntity de = new DistrictEntity();
			de.setId(rs.getLong("id"));
			de.setCode(rs.getString("code"));
			de.setName(rs.getString("name"));
			results.add(de);
		}
		return results;
	}

	public static List<RentAreaEntity> resultSetToRentAreaEntities(ResultSet rs) throws SQLException {
		List<RentAreaEntity> results = new ArrayList<>();
		while (rs.next()) {
			RentAreaEntity ra = new RentAreaEntity();
			ra.setId(rs.getLong("id"));
			ra.setValue(rs.getInt("value"));
			ra.setBuildingId(rs.getLong("buildingid"));
			results.add(ra);
		}
		return results;
	}
}
