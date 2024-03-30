package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.ConnectionUtil;
import com.javaweb.utils.NumberUtil;
import com.javaweb.utils.StringUtil;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {
	private static final Set<String> NUMERIC_FIELDS = new HashSet<>(Arrays.asList("floorArea", "areaFrom", "areaTo",
			"rentPriceFrom", "rentPriceTo", "numberOfBasement", "districtId", "staffId"));
	private static final Set<String> LIKE_FIELDS = new HashSet<>(
			Arrays.asList("name", "ward", "street", "direction", "level", "managerName", "managerPhoneNumber"));
	private static final Set<String> EQUAL_FIELDS = new HashSet<>(
			Arrays.asList("floorArea", "districtId", "numberOfBasement"));
	private static final String STAFF_ID_FIELD = "staffId";

	@Override
	public List<BuildingEntity> findAll(Map<String, Object> params, List<String> typeCode) {
		checkNumericFields(params);

		// build SQL query
		StringBuilder select = new StringBuilder("SELECT ");
		StringBuilder distinct = new StringBuilder();
		StringBuilder where = new StringBuilder("\nWHERE 1 = 1");
		StringBuilder join = new StringBuilder();
		StringBuilder columns = new StringBuilder("\r\n  b.*\r\nFROM building b");
		sqlWhereSimple(params, where);
		sqlWhereComplex(params, typeCode, where);
		sqlJoin(params, typeCode, join);
		if (!StringUtil.isEmpty(join)) {
			distinct.append("DISTINCT");
		}
		StringBuilder sql = select.append(distinct).append(columns).append(join).append(where);
		System.out.println(sql);

		// convert query's results to entities
		List<BuildingEntity> results = new ArrayList<>();
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement stm = con.prepareStatement(sql.toString());
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
			
		}
		return results;
	}

	private void checkNumericFields(Map<String, Object> params) {
		for (Map.Entry<String, Object> param : params.entrySet()) {
			if (StringUtil.isEmpty(param.getValue())) {
				continue;
			}
			if (!NUMERIC_FIELDS.contains(param.getKey())) {
				continue;
			}
			if (!NumberUtil.isNumber(param.getValue())) {
				throw new NumberFormatException(param.getKey());
			}
		}
	}

	private void sqlWhereSimple(Map<String, Object> params, StringBuilder where) {
		for (Map.Entry<String, Object> param : params.entrySet()) {
			if (StringUtil.isEmpty(param.getValue())) {
				continue;
			}
			String key = param.getKey();
			Object value = param.getValue();
			if (LIKE_FIELDS.contains(key)) {
				where.append("\n\tAND b." + key + " LIKE '%" + value.toString().trim() + "%'");
			} else if (EQUAL_FIELDS.contains(key)) {
				where.append("\n\tAND b." + key + " = " + value);
			} else if (STAFF_ID_FIELD.equals(key)) {
				where.append("\n\tAND asb.staffid = " + value);
			}
		}
	}

	private void sqlWhereComplex(Map<String, Object> params, List<String> typeCode, StringBuilder where) {
		if (!StringUtil.isEmpty(params.get("rentPriceFrom"))) {
			where.append("\n\tAND b.rentprice >= " + params.get("rentPriceFrom"));
		}

		if (!StringUtil.isEmpty(params.get("rentPriceTo"))) {
			where.append("\n\tAND b.rentprice <= " + params.get("rentPriceTo"));
		}

		if (!StringUtil.isEmpty(params.get("areaFrom"))) {
			where.append("\n\tAND ra.value >= " + params.get("areaFrom"));
		}

		if (!StringUtil.isEmpty(params.get("areaTo"))) {
			where.append("\n\tAND ra.value <= " + params.get("areaTo"));
		}

		if (StringUtil.usableTypeCode(typeCode)) {
			List<String> trimmedList = typeCode.stream().map(str -> "'" + str.trim() + "'")
					.collect(Collectors.toList());
			where.append("\n\tAND rt.code IN (").append(String.join(", ", trimmedList)).append(") ");
		}
	}

	private void sqlJoin(Map<String, Object> params, List<String> typeCode, StringBuilder join) {
		if (!StringUtil.isEmpty(params.get("staffId"))) {
			join.append("\nJOIN assignmentbuilding asb ON b.id = asb.buildingid");
		}
		if (!StringUtil.isEmpty(params.get("areaFrom")) || !StringUtil.isEmpty(params.get("areaTo"))) {
			join.append("\nJOIN rentarea ra ON b.id = ra.buildingid");
		}
		if (StringUtil.usableTypeCode(typeCode)) {
			join.append(
					"\nJOIN buildingrenttype brt ON brt.buildingid = b.id" + "\nJOIN renttype rt ON brt.id = rt.id");
		}
	}
}
