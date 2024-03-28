package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.ConnectionUtil;
import com.javaweb.utils.ConvertUtil;
import com.javaweb.utils.ValidationUtil;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {

	@Override
	public List<BuildingEntity> findAll(Map<String, Object> params, List<String> typeCode) {
		ValidationUtil.validateNumber(params);
		StringBuilder select = new StringBuilder("SELECT ");
		StringBuilder distinct = new StringBuilder();
		StringBuilder where = new StringBuilder("\nWHERE 1 = 1");
		StringBuilder join = new StringBuilder();
		StringBuilder columns = new StringBuilder("\r\n  b.*\r\nFROM building b");
		sqlWhereSimple(params, where);
		sqlWhereComplex(params, typeCode, where);
		sqlJoin(params, typeCode, join);
		if (!join.toString().trim().equals("")) {
			distinct.append("DISTINCT");
		}
		StringBuilder sql = select.append(distinct).append(columns).append(join).append(where);
		System.out.println(sql);
		
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement stm = con.prepareStatement(sql.toString());
				ResultSet rs = stm.executeQuery();) {
			return ConvertUtil.resultSetToBuildingEntities(rs);
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	private void sqlWhereSimple(Map<String, Object> params, StringBuilder where) {
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			if (!ValidationUtil.validateParam(entry)) {
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
				where.append("\n\tAND b." + entry.getKey() + " LIKE '%" + entry.getValue() + "%'");
				break;
			case "floorArea":
			case "districtId":
			case "numberOfBasement":
				where.append("\n\tAND b." + entry.getKey() + " = " + entry.getValue());
				break;
			case "staffId":
				where.append("\n\tAND asb.staffid = " + entry.getValue());
				break;
			}
		}
	}

	private void sqlWhereComplex(Map<String, Object> params, List<String> typeCode, StringBuilder where) {
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			if (ValidationUtil.validateParam(entry.getValue())) {
				continue;
			}
			switch (entry.getKey()) {
			case "rentPriceFrom":
				where.append("\n\tAND b.rentprice" + " >= " + entry.getValue());
				break;
			case "rentPriceTo":
				where.append("\n\tAND b.rentprice" + " <= " + entry.getValue());
				break;
			case "areaFrom":
				where.append("\n\tAND ra.value >= " + entry.getValue());
				break;
			case "areaTo":
				where.append("\n\tAND ra.value <= " + entry.getValue());
				break;
			}
		}
		if (ValidationUtil.validateTypeCode(typeCode)) {
			typeCode.stream().forEach(str -> where.append("\nOR rt.code LIKE '%" + str.trim() + "%'"));
		}
	}

	private void sqlJoin(Map<String, Object> params, List<String> typeCode, StringBuilder join) {
		if (ValidationUtil.validateParam(params.get("staffId"))) {
			join.append("\nJOIN assignmentbuilding asb ON b.id = asb.buildingid");
		}
		if (ValidationUtil.validateParam(params.get("areaFrom"))
				|| ValidationUtil.validateParam(params.get("areaTo"))) {
			join.append("\nJOIN rentarea ra ON b.id = ra.buildingid");
		}

		if (ValidationUtil.validateTypeCode(typeCode)) {
			join.append(
					"\nJOIN buildingrenttype brt ON brt.buildingid = b.id" + "\nJOIN renttype rt ON brt.id = rt.id");
		}
	}

}