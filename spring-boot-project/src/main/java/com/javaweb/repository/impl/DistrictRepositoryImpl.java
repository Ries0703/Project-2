package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.utils.ConnectionUtil;
import com.javaweb.utils.ConvertUtil;

@Repository
public class DistrictRepositoryImpl implements DistrictRepository {

	@Override
	public DistrictEntity getById(long districtId) {
		String sql = makeSQLSelectDistrict(districtId);
		System.out.println(sql);
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement stm = con.prepareStatement(sql.toString());
				ResultSet rs = stm.executeQuery();) {
			List<DistrictEntity> results = ConvertUtil.resultSetToDistrictEntities(rs);
			return results.isEmpty() ? null : results.get(0);
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	private String makeSQLSelectDistrict(long districtId) {
		StringBuilder sql = new StringBuilder("SELECT\r\n" + "  *\r\n" + "FROM\r\n" + "  district");
		StringBuilder condition = new StringBuilder("\nWHERE 1 = 1 AND id = " + districtId);
		sql.append(condition);
		return sql.toString();
	}
}
