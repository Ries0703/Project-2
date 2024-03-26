package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.stereotype.Repository;

import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.RentAreaEntity;
import com.javaweb.utils.ConnectionUtil;

@Repository
public class RentAreaRepositoryImpl implements RentAreaRepository {

	@Override
	public String getByBuildingId(long buildingId) {
		String sql = makeSQLSelectRentArea(buildingId);
		System.out.println(sql);

		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement stm = con.prepareStatement(sql.toString());
				ResultSet rs = stm.executeQuery();) {
			List<RentAreaEntity> rentAreas = resultSetToEntities(rs);
			return StringUtils.join(rentAreas.stream()
											.map(o -> o.getValue().toString())
											.collect(Collectors.toList()), ',');
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

	private String makeSQLSelectRentArea(long buildingId) {
		StringBuilder sql = new StringBuilder("SELECT\r\n" + "  *\r\n" + "FROM\r\n" + "  rentarea");
		StringBuilder condition = new StringBuilder("\nWHERE 1 = 1 AND buildingid = " + buildingId);
		sql.append(condition);
		return sql.toString();
	}
}
