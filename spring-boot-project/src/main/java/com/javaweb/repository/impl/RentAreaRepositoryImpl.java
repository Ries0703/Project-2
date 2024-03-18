package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.RentAreaEntity;
import com.javaweb.utils.ConnectionUtil;

@Repository
public class RentAreaRepositoryImpl implements RentAreaRepository {

	@Override
	public List<RentAreaEntity> findAll(List<BuildingEntity> buildings) {
		String sql = makeSQLSelectRentArea(buildings);
		System.out.println(sql);

		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement stm = con.prepareStatement(sql.toString());
				ResultSet rs = stm.executeQuery();) {
			return resultSetToEntities(rs);
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}

	}

	private List<RentAreaEntity> resultSetToEntities(ResultSet rs) throws SQLException {
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

	private String makeSQLSelectRentArea(List<BuildingEntity> buildings) {
		StringBuilder sql = new StringBuilder("SELECT\r\n" + "  *\r\n" + "FROM\r\n" + "  rentarea");
		StringBuilder condition = new StringBuilder("\nWHERE 1 = 1 AND buildingid IN (");

		for (int i = 0; i < buildings.size(); i++) {
			condition.append(buildings.get(i).getId());
			if (i == buildings.size() - 1) {
				condition.append(")");
				break;
			}
			condition.append(", ");

		}

		sql.append(condition);
		return sql.toString();
	}
}
