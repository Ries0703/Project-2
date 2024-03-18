package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.ConnectionUtil;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {

	@Override
	public List<BuildingEntity> findAll(Map<String, Object> params, List<String> typeCode) {
		String operation = "SELECT ";
		String distinct = "";
		String columns = "\r\n  b.id,\r\n"
				+ "  b.name,\r\n"
				+ "  b.street,\r\n"
				+ "  b.ward,\r\n"
				+ "  b.districtid,\r\n"
				+ "  b.numberofbasement,\r\n"
				+ "  b.managername,\r\n"
				+ "  b.managerphonenumber,\r\n"
				+ "  b.floorarea,\r\n"
				+ "  b.brokeragefee,\r\n"
				+ "  b.servicefee,\r\n"
				+ "  b.rentprice\r\n"
				+ "FROM\r\n"
				+ "  building b";

		String condition = "\nWHERE 1 = 1";
		String join = "";
		
		boolean joinedRentArea = false;
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			if (entry.getValue() == null || entry.getValue().toString().trim().equals("")) {
				continue;
			}
			switch (entry.getKey()) {
			case "name":
			case "ward":
			case "street":
			case "direction":
			case "level":
			case "managerName":
			case "managerPhoneNumber":
				condition += "\n\tAND b." + entry.getKey() + " LIKE '%" + entry.getValue() + "%'";
				break;
			case "floorArea":
			case "districtId":
			case "numberOfBasement":
				condition += "\n\tAND b." + entry.getKey() + " = " + entry.getValue();
				break;
			case "rentPriceFrom":
				condition += "\n\tAND b.rentprice" + " >= " + entry.getValue();
				break;
			case "rentPriceTo":
				condition += "\n\tAND b.rentprice" + " <= " + entry.getValue();
				break;
			case "staffId":
				join += "\nJOIN assignmentbuilding asb ON b.id = asb.buildingid AND asb.staffid = " + entry.getValue();
				distinct = "DISTINCT";
				break;
			case "areaFrom": case "areaTo":
				if (!joinedRentArea) {
					joinedRentArea = true;
					join += "\nJOIN rentarea ra ON b.id = ra.buildingid";
				}
				if (entry.getKey().equals("areaFrom")) {
					join += " AND ra.value >= " + entry.getValue();
				} else if (entry.getKey().equals("areaTo")) {
					join += " AND ra.value <= " + entry.getValue();
				}
				distinct = "DISTINCT";
			}
		}
		
		boolean typeCodeUnuseable = true;
		if (typeCode != null && typeCode.size() != 0) {
			for (String str : typeCode) {              
				if (str != null && !str.equals("")) {
					typeCodeUnuseable = false;
					break;
				}
			}
		}
		
		if (!typeCodeUnuseable) {
			distinct = "DISTINCT";
			join += "\nJOIN buildingrenttype brt ON brt.buildingid = b.id"
				  + "\nJOIN renttype rt ON brt.id = rt.id AND (1 = 1";
			for (String str : typeCode) {              
				join += "\nOR rt.code LIKE '%" + str + "%'";
			}
			join += ")";
		}
		
		String sql = operation + distinct + columns + join + condition + ";";
		System.out.println(sql);

		List<BuildingEntity> results = new ArrayList<>();
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement stm = con.prepareStatement(sql);
				ResultSet rs = stm.executeQuery();) {
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
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}
		return results;
	}
	
}
