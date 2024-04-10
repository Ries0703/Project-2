package com.javaweb.repository.custom.impl;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.StringUtil;

@Repository
public class BuildingRepositoryCustomImpl implements BuildingRepositoryCustom {
	private static final List<String> LIKE_FIELDS = Arrays.asList("name", "ward", "street", "direction", "level",
			"managerName", "managerPhoneNumber");
	private static final List<String> EQUAL_FIELDS = Arrays.asList("floorArea", "districtId", "numberOfBasement");
	private static final String STAFF_ID_FIELD = "staffId";

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {

		// build SQL query
		StringBuilder select = new StringBuilder("SELECT ");
		StringBuilder distinct = new StringBuilder();
		StringBuilder columns = new StringBuilder("  b.* FROM building b ");
		StringBuilder join = new StringBuilder();
		StringBuilder where = new StringBuilder(" WHERE 1 = 1");
		sqlWhereSimple(buildingSearchBuilder, where);
		sqlWhereComplex(buildingSearchBuilder, where);
		sqlJoin(buildingSearchBuilder, join);
		if (!StringUtil.isEmpty(join)) {
			distinct.append(" DISTINCT ");
		}
		StringBuilder sql = select.append(distinct).append(columns).append(join).append(where);
		
		// get results 
		return entityManager
				.createNativeQuery(sql.toString(), BuildingEntity.class)
				.getResultList();
	}

	private void sqlWhereSimple(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
		for (Field field : BuildingSearchBuilder.class.getDeclaredFields()) {
			try {
				field.setAccessible(true);
				String key = field.getName();
				Object value = field.get(buildingSearchBuilder);
				if (StringUtil.isEmpty(value)) {
					continue;
				}
				if (LIKE_FIELDS.contains(key)) {
					where.append(" AND b." + key + " LIKE '%" + value.toString().trim() + "%' ");
				} else if (EQUAL_FIELDS.contains(key)) {
					where.append(" AND b." + key + " = " + value);
				} else if (STAFF_ID_FIELD.equals(key)) {
					where.append(" AND asb.staffid = " + value);
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	private void sqlWhereComplex(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
		if (!StringUtil.isEmpty(buildingSearchBuilder.getRentPriceFrom())) {
			where.append(" AND b.rentprice >= " + buildingSearchBuilder.getRentPriceFrom());
		}

		if (!StringUtil.isEmpty(buildingSearchBuilder.getRentPriceTo())) {
			where.append(" AND b.rentprice <= " + buildingSearchBuilder.getRentPriceTo());
		}

		if (!StringUtil.isEmpty(buildingSearchBuilder.getAreaFrom())) {
			where.append(" AND ra.value >= " + buildingSearchBuilder.getAreaFrom());
		}

		if (!StringUtil.isEmpty(buildingSearchBuilder.getAreaTo())) {
			where.append(" AND ra.value <= " + buildingSearchBuilder.getAreaTo());
		}

		if (StringUtil.usableTypeCode(buildingSearchBuilder.getTypeCodes())) {
			List<String> trimmedList = buildingSearchBuilder.getTypeCodes().stream().map(str -> "'" + str.trim() + "'")
					.collect(Collectors.toList());
			where.append(" AND rt.code IN (").append(String.join(", ", trimmedList)).append(") ");
		}
	}

	private void sqlJoin(BuildingSearchBuilder buildingSearchBuilder, StringBuilder join) {
		if (!StringUtil.isEmpty(buildingSearchBuilder.getStaffId())) {
			join.append(" JOIN assignmentbuilding asb ON b.id = asb.buildingid");
		}
		if (!StringUtil.isEmpty(buildingSearchBuilder.getAreaFrom())
				|| !StringUtil.isEmpty(buildingSearchBuilder.getAreaTo())) {
			join.append(" JOIN rentarea ra ON b.id = ra.buildingid");
		}
		if (StringUtil.usableTypeCode(buildingSearchBuilder.getTypeCodes())) {
			join.append(" JOIN buildingrenttype brt ON brt.buildingid = b.id JOIN renttype rt ON brt.renttypeid = rt.id");
		}
	}
}

