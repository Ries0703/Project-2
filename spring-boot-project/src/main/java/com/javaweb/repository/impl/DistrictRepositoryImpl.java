package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.utils.ConnectionUtil;

@Repository
public class DistrictRepositoryImpl implements DistrictRepository {

	@Override
	public DistrictEntity getById(Long districtId) {
		StringBuilder sql = new StringBuilder("SELECT * FROM district WHERE id = ").append(districtId);
//		System.out.println(sql);

		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement stm = con.prepareStatement(sql.toString());
				ResultSet rs = stm.executeQuery();) {
			List<DistrictEntity> results = new ArrayList<>();
			while (rs.next()) {
				DistrictEntity de = new DistrictEntity();
				de.setId(rs.getLong("id"));
				de.setCode(rs.getString("code"));
				de.setName(rs.getString("name"));
				results.add(de);
			}
			return results.isEmpty() ? null : results.get(0);
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
