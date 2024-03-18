package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.utils.ConnectionUtil;

@Repository
public class DistrictRepositoryImpl implements DistrictRepository {

	@Override
	public List<DistrictEntity> findAll(List<BuildingEntity> buildings) {
		StringBuilder sql = new StringBuilder("SELECT\r\n" + "  *\r\n" + "FROM\r\n" + "  district");
		StringBuilder condition = new StringBuilder("\nWHERE 1 = 1 AND id IN (");
		List<DistrictEntity> results = new ArrayList<>();
		
		
		
		for (BuildingEntity entity : buildings) {
				condition.append(entity.getDistrictId()).append(", ");
		}
		condition.delete(condition.length() - 2, condition.length());
		condition.append(")");

		sql.append(condition);
		System.out.println(sql);
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement stm = con.prepareStatement(sql.toString());
				ResultSet rs = stm.executeQuery();) {
			while (rs.next()) {
				DistrictEntity de = new DistrictEntity();
				de.setId(rs.getLong("id"));
				de.setCode(rs.getString("code"));
				de.setName(rs.getString("name"));
				results.add(de);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}

		return results;
	}

}
