package com.javaweb.utils;

public class MapUtil {
	public static Long getLong(Object value) throws NumberFormatException {
		if (StringUtil.isEmpty(value)) {
			return null;
		}
		try {
			return Long.parseLong(value.toString().trim());
		} catch (NumberFormatException nfe) {
			throw new NumberFormatException(" '" + value.toString() + "' " + " is not a number");
		}

	}

	public static String getString(Object value) {
		if (value == null) {
			return null;
		}
		return value.toString();
	}
}
