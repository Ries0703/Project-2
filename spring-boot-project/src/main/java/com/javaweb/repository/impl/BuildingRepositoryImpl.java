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
		String sql = makeSQLSelectBuilding(params, typeCode);
		System.out.println(sql);

		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement stm = con.prepareStatement(sql);
				ResultSet rs = stm.executeQuery();) {
			return resultSetToBuildingEntities(rs);
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	private String makeSQLSelectBuilding(Map<String, Object> params, List<String> typeCode) {
		StringBuilder operation = new StringBuilder("SELECT ");
		String distinct = "";
		String condition = makeSQLWhere(params, typeCode);
		String join = makeSQLJoin(params, typeCode);

		String columns = "\r\n  b.id,\r\n" + "  b.name,\r\n" + "  b.street,\r\n" + "  b.ward,\r\n"
				+ "  b.districtid,\r\n" + "  b.numberofbasement,\r\n" + "  b.managername,\r\n"
				+ "  b.managerphonenumber,\r\n" + "  b.floorarea,\r\n" + "  b.brokeragefee,\r\n" + "  b.servicefee,\r\n"
				+ "  b.rentprice\r\n" + "FROM\r\n" + "  building b";

		if (!join.trim().equals("")) {
			distinct = "DISTINCT";
		}

		StringBuilder sql = operation.append(distinct).append(columns).append(join).append(condition);
		return sql.toString();
	}

	private String makeSQLWhere(Map<String, Object> params, List<String> typeCode) {
		StringBuilder condition = new StringBuilder("\nWHERE 1 = 1 ");
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
				condition.append("\n\tAND b." + entry.getKey() + " LIKE '%" + entry.getValue() + "%'");
				break;
			case "floorArea":
			case "districtId":
			case "numberOfBasement":
				condition.append("\n\tAND b." + entry.getKey() + " = " + entry.getValue());
				break;
			case "rentPriceFrom":
				condition.append("\n\tAND b.rentprice" + " >= " + entry.getValue());
				break;
			case "rentPriceTo":
				condition.append("\n\tAND b.rentprice" + " <= " + entry.getValue());
				break;
			}
		}
		return condition.toString();
	}

	private String makeSQLJoin(Map<String, Object> params, List<String> typeCode) {
		StringBuilder join = new StringBuilder();
		boolean joinedRentArea = false;
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			if (entry.getValue() == null || entry.getValue().toString().trim().equals("")) {
				continue;
			}
			switch (entry.getKey()) {
			case "staffId":
				join.append(
						"\nJOIN assignmentbuilding asb ON b.id = asb.buildingid AND asb.staffid = " + entry.getValue());
				break;
			case "areaFrom":
			case "areaTo":
				if (!joinedRentArea) {
					joinedRentArea = true;
					join.append("\nJOIN rentarea ra ON b.id = ra.buildingid");
				}
				if (entry.getKey().equals("areaFrom")) {
					join.append("\n\tAND ra.value >= " + entry.getValue());
				} else if (entry.getKey().equals("areaTo")) {
					join.append("\n\tAND ra.value <= " + entry.getValue());
				}
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
			join.append("\nJOIN buildingrenttype brt ON brt.buildingid = b.id"
					+ "\nJOIN renttype rt ON brt.id = rt.id AND (1 = 1");
			for (String str : typeCode) {
				join.append("\nOR rt.code LIKE '%" + str + "%'");
			}
			join.append(")");
		}
		return join.toString();
	}

	private List<BuildingEntity> resultSetToBuildingEntities(ResultSet rs) throws SQLException {
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
}
