package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.RentAreaEntity;
import com.javaweb.utils.ConnectionUtil;

@Repository
public class RentAreaRepositoryImpl implements RentAreaRepository {

	@Override
	public List<String> getByBuildingId(long buildingId) {
		StringBuilder sql = new StringBuilder("SELECT * FROM rentarea WHERE buildingid = ").append(buildingId);
//		System.out.println(sql);

		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement stm = con.prepareStatement(sql.toString());
				ResultSet rs = stm.executeQuery();) {
			List<RentAreaEntity> rentAreas = new ArrayList<>();
			while (rs.next()) {
				RentAreaEntity ra = new RentAreaEntity();
				ra.setId(rs.getLong("id"));
				ra.setValue(rs.getInt("value"));
				ra.setBuildingId(rs.getLong("buildingid"));
				rentAreas.add(ra);
			}
			List<String> values = rentAreas.stream().map(o -> o.getValue().toString()).collect(Collectors.toList());
			return values;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}
}