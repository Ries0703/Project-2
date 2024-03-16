package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.utils.ConnectionUtil;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {

	@Override
	public List<Map<String, Object>> findAll(Map<String, Object> params) {
		String sql = "SELECT\r\n"
				+ "  b.name,\r\n"
				+ "  b.street,\r\n"
				+ "  b.ward,\r\n"
				+ "  d.name,\r\n"
				+ "  b.numberofbasement,\r\n"
				+ "  b.managername,\r\n"
				+ "  b.managerphonenumber,\r\n"
				+ "  b.floorarea,\r\n"
				+ "  GROUP_CONCAT(r.value SEPARATOR ', ') as rentareas,\r\n"
				+ "  b.brokeragefee,\r\n"
				+ "  b.servicefee,\r\n"
				+ "  b.rentprice\r\n"
				+ "FROM\r\n"
				+ "  building b\r\n"
				+ "  JOIN district d ON b.districtid = d.id\r\n"
				+ "  JOIN rentarea r ON b.id = r.buildingid\r\n"
				+ "  JOIN assignmentbuilding asb on b.id = asb.buildingid\n";

		String condition = "WHERE 1 = 1";
		String groupBy = "\nGROUP BY b.id";
		

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			if (((String) entry.getValue()).equals("")) {
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
					
				case "areaFrom":
					condition += "\n\tAND r.value" + " >= " + entry.getValue();
					break;
				case "areaTo":
					condition += "\n\tAND r.value" + " <= " + entry.getValue();
					break;
					
				case "staffId":
					condition += "\n\tAND asb.staffid = " + entry.getValue();
				}
		}

		sql += condition + groupBy + ";";
		System.out.println(sql);
		
		List<Map<String, Object>> results = new ArrayList<>();
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement stm = con.prepareStatement(sql);
				ResultSet rs = stm.executeQuery();) {

			while (rs.next()) {
				Map<String, Object> result = new HashMap<>();
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				for (int i = 1; i <= columnCount; i++) {
					String key = metaData.getColumnName(i);
					Object value = rs.getObject(key);
					result.put(key, value);
				}
				results.add(result);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}
		return results;
	}

}
