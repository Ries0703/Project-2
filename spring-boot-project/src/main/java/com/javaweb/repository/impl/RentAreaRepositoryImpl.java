package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.stereotype.Repository;

import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.RentAreaEntity;
import com.javaweb.utils.ConnectionUtil;
import com.javaweb.utils.ConvertUtil;

@Repository
public class RentAreaRepositoryImpl implements RentAreaRepository {

	@Override
	public String getByBuildingId(long buildingId) {
		String sql = makeSQLSelectRentArea(buildingId);
		System.out.println(sql);

		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement stm = con.prepareStatement(sql.toString());
				ResultSet rs = stm.executeQuery();) {
			List<RentAreaEntity> rentAreas = ConvertUtil.resultSetToRentAreaEntities(rs);
			return StringUtils.join(rentAreas.stream().map(o -> o.getValue().toString()).collect(Collectors.toList()),
					',');
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	private String makeSQLSelectRentArea(long buildingId) {
		StringBuilder sql = new StringBuilder("SELECT\r\n" + "  *\r\n" + "FROM\r\n" + "  rentarea");
		StringBuilder condition = new StringBuilder("\nWHERE 1 = 1 AND buildingid = " + buildingId);
		sql.append(condition);
		return sql.toString();
	}
}
