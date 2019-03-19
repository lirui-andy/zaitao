package com.yichang.uep.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	static SimpleDateFormat sdf_dash_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static SimpleDateFormat sdf_slash4 = new SimpleDateFormat("MM/dd/yyyy");
	
	public static Date parse(String s) {
		try {
			if(s.indexOf("-") > -1 && s.indexOf(":") > -1)
				return sdf_dash_time.parse(s);
			else if(s.indexOf("/") > -1) {
				String[] ss = s.split("/");
				if(ss[2].length() == 2) {
					ss[2]="20"+ss[2];
					return sdf_slash4.parse(String.format("%1$s/%2$s/%3$s", ss[0], ss[1], ss[2]));
				}
				return sdf_slash4.parse(s);
			}
			else
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
