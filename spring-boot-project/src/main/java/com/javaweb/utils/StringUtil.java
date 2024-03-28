package com.javaweb.utils;

import java.util.List;

import org.springframework.util.StringUtils;

public class StringUtil {
	public static boolean usableTypeCode(List<String> typeCodes) {
		if (typeCodes == null)
			return false;
		return typeCodes.stream().filter(str -> !StringUtils.isEmpty(str)).count() > 0;
	}
	public static boolean isEmpty(Object str) {
		return str == null || str.toString().trim().equals("");
	}
}
