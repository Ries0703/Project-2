package com.javaweb.utils;

public class NumberUtil {
	public static boolean isNumber(Object value) {
		return value.toString().trim().matches("-?\\d+(\\.\\d+)?");
	}
}
