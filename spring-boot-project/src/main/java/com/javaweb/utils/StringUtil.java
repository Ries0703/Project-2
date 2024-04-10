package com.javaweb.utils;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class StringUtil {
	public static boolean usableTypeCode(List<String> typeCodes) {
		if (typeCodes == null)
			return false;
		return typeCodes.stream().anyMatch(str -> !StringUtil.isEmpty(str));

	}

	public static boolean isEmpty(Object str) {
		return str == null || StringUtils.isBlank(str.toString());
	}
}
