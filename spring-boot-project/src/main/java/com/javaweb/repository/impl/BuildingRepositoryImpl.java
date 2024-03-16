package com.javaweb.repository.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {
	private final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	private final String USER = "root";
	private final String PASS = "0000";

	@Override
	public List<BuildingEntity> findAll(Map<String, Object> params) {
		StringBuilder query = new StringBuilder("SELECT * FROM building ");
		StringBuilder condition = new StringBuilder(" WHERE 1 = 1 ");
		for (Map.Entry<String, Object> param : params.entrySet()) {
			if (param.getValue() == null) {
				continue;
			}
			if (param.getValue().toString().matches("-?\\d+(\\.\\d+)?")) {
				condition.append(" AND " + param.getKey() + " = " + param.getValue());
			} else if (!param.getValue().equals("")) {
				condition.append(" AND " + param.getKey() + " LIKE " + "'%" + param.getValue() + "%' ");
			}
		}
		query.append(condition).append(";");
		List<BuildingEntity> buildingEntities = new ArrayList<BuildingEntity>();
		try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stm = con.createStatement();
				ResultSet rs = stm.executeQuery(query.toString());) {
			while (rs.next()) {
				BuildingEntity buildingEntity = new BuildingEntity();
				buildingEntity.setDistrictId(rs.getLong("districtid"));
				buildingEntity.setName(rs.getString("name"));
				buildingEntity.setStreet(rs.getString("street"));
				buildingEntity.setWard(rs.getString("ward"));
				buildingEntities.add(buildingEntity);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return buildingEntities;
	}

	@Override
	public List<BuildingEntity> save(List<BuildingEntity> buildingEntity) {
		StringBuilder insert = new StringBuilder();

		for (BuildingEntity be : buildingEntity) {
			insert.append("INSERT INTO building(name, street, ward, districtid) VALUES\n");
			insert.append("(\"" + be.getName() + "\", ");
			insert.append("\"" + be.getStreet() + "\", ");
			insert.append("\"" + be.getWard() + "\", ");
			insert.append(be.getDistrictId());
			insert.append(");");
		}
		System.out.println(insert.toString());
		StringBuilder query2 = new StringBuilder("SELECT * FROM building WHERE id = LAST_INSERT_ID();");
		List<BuildingEntity> buildingEntities = new ArrayList<>();
		try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS); Statement stm = con.createStatement();) {
			stm.executeUpdate(insert.toString());
			ResultSet rs = stm.executeQuery(query2.toString());
			while (rs.next()) {
				BuildingEntity temp = new BuildingEntity();
				temp.setDistrictId(rs.getLong("districtid"));
				temp.setName(rs.getString("name"));
				temp.setStreet(rs.getString("street"));
				temp.setWard(rs.getString("ward"));
				buildingEntities.add(temp);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return buildingEntities;
	}

	@Override
	public String delete(List<Long> ids) {
		StringBuilder query = new StringBuilder("DELETE FROM building ");
		StringBuilder conditions = new StringBuilder("WHERE 1 = 1 ");
		for (Long id : ids) {
			if(ids == null) continue;
			conditions.append("OR id = " + id);
		}
		query.append(conditions).append(";");
		try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS);) {
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return "delete succeeded";
		
	}



}
