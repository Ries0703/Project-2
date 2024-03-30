package com.javaweb.utils;

import org.apache.commons.lang3.math.NumberUtils;

public class NumberUtil {
	public static boolean isNumber(Object value) {
	    return value != null && NumberUtils.isCreatable(value.toString().trim());
	}
}
