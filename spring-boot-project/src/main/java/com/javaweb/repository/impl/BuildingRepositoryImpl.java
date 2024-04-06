package com.javaweb.repository.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.ConnectionUtil;
import com.javaweb.utils.StringUtil;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {
	private static final List<String> LIKE_FIELDS = Arrays.asList("name", "ward", "street", "direction", "level",
			"managerName", "managerPhoneNumber");
	private static final List<String> EQUAL_FIELDS = Arrays.asList("floorArea", "districtId", "numberOfBasement");
	private static final String STAFF_ID_FIELD = "staffId";

	@Override
	public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
		// build SQL query
		StringBuilder select = new StringBuilder("SELECT ");
		StringBuilder distinct = new StringBuilder();
		StringBuilder columns = new StringBuilder("\r\n  b.*\r\nFROM building b");
		StringBuilder join = new StringBuilder();
		StringBuilder where = new StringBuilder("\nWHERE 1 = 1");
		sqlWhereSimple(buildingSearchBuilder, where);
		sqlWhereComplex(buildingSearchBuilder, where);
		sqlJoin(buildingSearchBuilder, join);
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
//				building.setDistrictId(rs.getLong("districtid"));
				building.setNumberOfBasement(rs.getLong("numberofbasement"));
				building.setManagerName(rs.getString("managername"));
				building.setManagerPhoneNumber(rs.getString("managerphonenumber"));
				building.setFloorArea(rs.getLong("floorarea"));
				building.setBrokerageFee(rs.getDouble("brokeragefee"));
				building.setServiceFee(rs.getString("servicefee"));
				building.setRentPrice(rs.getLong("rentprice"));
				results.add(building);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();

		}
		return results;
	}

	private void sqlWhereSimple(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
		for (Field field : BuildingSearchBuilder.class.getDeclaredFields()) {
			try {
				field.setAccessible(true);
				String fieldName = field.getName();
				Object value = field.get(buildingSearchBuilder);
				if (StringUtil.isEmpty(value)) {
					continue;
				}
				if (LIKE_FIELDS.contains(fieldName)) {
					where.append("\n\tAND b." + fieldName + " LIKE '%" + value.toString().trim() + "%'");
				} else if (EQUAL_FIELDS.contains(fieldName)) {
					where.append("\n\tAND b." + fieldName + " = " + value);
				} else if (STAFF_ID_FIELD.equals(fieldName)) {
					where.append("\n\tAND asb.staffid = " + value);
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	private void sqlWhereComplex(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
		if (!StringUtil.isEmpty(buildingSearchBuilder.getRentPriceFrom())) {
			where.append("\n\tAND b.rentprice >= " + buildingSearchBuilder.getRentPriceFrom());
		}

		if (!StringUtil.isEmpty(buildingSearchBuilder.getRentPriceTo())) {
			where.append("\n\tAND b.rentprice <= " + buildingSearchBuilder.getRentPriceTo());
		}

		if (!StringUtil.isEmpty(buildingSearchBuilder.getAreaFrom())) {
			where.append("\n\tAND ra.value >= " + buildingSearchBuilder.getAreaFrom());
		}

		if (!StringUtil.isEmpty(buildingSearchBuilder.getAreaTo())) {
			where.append("\n\tAND ra.value <= " + buildingSearchBuilder.getAreaTo());
		}

		if (StringUtil.usableTypeCode(buildingSearchBuilder.getTypeCodes())) {
			List<String> trimmedList = buildingSearchBuilder.getTypeCodes().stream().map(str -> "'" + str.trim() + "'")
					.collect(Collectors.toList());
			where.append("\n\tAND rt.code IN (").append(String.join(", ", trimmedList)).append(") ");
		}
	}

	private void sqlJoin(BuildingSearchBuilder buildingSearchBuilder, StringBuilder join) {
		if (!StringUtil.isEmpty(buildingSearchBuilder.getStaffId())) {
			join.append("\nJOIN assignmentbuilding asb ON b.id = asb.buildingid");
		}
		if (!StringUtil.isEmpty(buildingSearchBuilder.getAreaFrom())
				|| !StringUtil.isEmpty(buildingSearchBuilder.getAreaTo())) {
			join.append("\nJOIN rentarea ra ON b.id = ra.buildingid");
		}
		if (StringUtil.usableTypeCode(buildingSearchBuilder.getTypeCodes())) {
			join.append(
					"\nJOIN buildingrenttype brt ON brt.buildingid = b.id\nJOIN renttype rt ON brt.id = rt.id");
		}
	}
}
