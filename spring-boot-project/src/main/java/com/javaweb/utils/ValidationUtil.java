package com.javaweb.utils;

import java.util.List;
import java.util.Map;

public class ValidationUtil {
	public static void validateNumber(Map<String, Object> params) {
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			if (validateParam(entry.getValue())) {
				continue;
			}
			switch (entry.getKey()) {
			case "floorArea":
			case "areaFrom":
			case "areaTo":
			case "rentPriceFrom":
			case "rentPriceTo":
			case "numberOfBasement":
				Double.parseDouble(entry.getValue().toString());
				break;
			}
		}
	}

	public static boolean validateTypeCode(List<String> typeCodes) {
		if (typeCodes == null)
			return false;
		return typeCodes.stream().filter(typeCode -> typeCode != null && !typeCode.trim().equals("")).count() > 0;
	}

	public static boolean validateParam(Object entry) {
		return entry != null && entry.toString().trim().equals("");

	}
}
