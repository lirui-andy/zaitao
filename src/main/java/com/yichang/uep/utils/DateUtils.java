package com.yichang.uep.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public static Date parse(String s) {
		try {
			return sdf.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Date addDay(Date d, int days){
		if(d == null) return null;
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.DAY_OF_MONTH, days);
		return c.getTime();
	}
	
	/**
	 * 保留日期，时间截断到0时0分0秒
	 * @param d
	 * @return
	 */
	public static Date truncate(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.clear();
		c.set(d.getYear(), d.getMonth(), d.getDate());
		return c.getTime();
	}
}
