package com.javaweb.repository.impl;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Arrays;
import java.util.List;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {
    private static final List<String> LIKE_FIELDS = Arrays.asList("name", "ward", "street", "direction", "level",
            "managerName", "managerPhoneNumber");
    private static final List<String> EQUAL_FIELDS = Arrays.asList("floorArea", "districtId", "numberOfBasement");
    private static final String STAFF_ID_FIELD = "staffId";

    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
        String jpql = "FROM BuildingEntity b WHERE 1 = 1";
        Query query = entityManager.createQuery(jpql, BuildingEntity.class);
        return query.getResultList();
    }
//
//	private void sqlWhereSimple(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
//		for (Field field : BuildingSearchBuilder.class.getDeclaredFields()) {
//			try {
//				field.setAccessible(true);
//				String fieldName = field.getName();
//				Object value = field.get(buildingSearchBuilder);
//				if (StringUtil.isEmpty(value)) {
//					continue;
//				}
//				if (LIKE_FIELDS.contains(fieldName)) {
//					where.append("\n\tAND b." + fieldName + " LIKE '%" + value.toString().trim() + "%'");
//				} else if (EQUAL_FIELDS.contains(fieldName)) {
//					where.append("\n\tAND b." + fieldName + " = " + value);
//				} else if (STAFF_ID_FIELD.equals(fieldName)) {
//					where.append("\n\tAND asb.staffid = " + value);
//				}
//			} catch (IllegalArgumentException e) {
//				e.printStackTrace();
//			} catch (IllegalAccessException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	private void sqlWhereComplex(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
//		if (!StringUtil.isEmpty(buildingSearchBuilder.getRentPriceFrom())) {
//			where.append("\n\tAND b.rentprice >= " + buildingSearchBuilder.getRentPriceFrom());
//		}
//
//		if (!StringUtil.isEmpty(buildingSearchBuilder.getRentPriceTo())) {
//			where.append("\n\tAND b.rentprice <= " + buildingSearchBuilder.getRentPriceTo());
//		}
//
//		if (!StringUtil.isEmpty(buildingSearchBuilder.getAreaFrom())) {
//			where.append("\n\tAND ra.value >= " + buildingSearchBuilder.getAreaFrom());
//		}
//
//		if (!StringUtil.isEmpty(buildingSearchBuilder.getAreaTo())) {
//			where.append("\n\tAND ra.value <= " + buildingSearchBuilder.getAreaTo());
//		}
//
//		if (StringUtil.usableTypeCode(buildingSearchBuilder.getTypeCodes())) {
//			List<String> trimmedList = buildingSearchBuilder.getTypeCodes().stream().map(str -> "'" + str.trim() + "'")
//					.collect(Collectors.toList());
//			where.append("\n\tAND rt.code IN (").append(String.join(", ", trimmedList)).append(") ");
//		}
//	}


}
