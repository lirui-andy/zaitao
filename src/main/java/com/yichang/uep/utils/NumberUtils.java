package com.yichang.uep.utils;

public class NumberUtils extends org.springframework.util.NumberUtils {

	public static Integer parseIntOrNull(String str) {
		try {
			return parseNumber(str, Integer.class);
		} catch (Exception e) {
			return null;
		} 
	}
}
