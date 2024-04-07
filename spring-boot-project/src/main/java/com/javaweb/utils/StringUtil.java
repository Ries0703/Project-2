package com.javaweb.utils;

import java.util.List;

public class StringUtil {
	public static boolean usableTypeCode(List<String> typeCodes) {
		if (typeCodes == null)
			return false;
		return typeCodes.stream().anyMatch(str -> !StringUtil.isEmpty(str));

	}

	public static boolean isEmpty(Object str) {
		return str == null || str.toString().trim().equals("");
	}
}
