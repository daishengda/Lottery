package com.dsd.lottery.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * fsdgfdg
 * 
 * @author daishengda
 *
 */
public class DateUtil {
	/**
	 * StringToDate
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Date StringToDate(String dateStr) {
		if (dateStr == null || dateStr.equals("")) {
			return null;
		}
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * StringToDate
	 * 
	 * @param dateStr
	 * @param formatStr
	 * @return
	 */
	public static Date StringToDate(String dateStr, String formatStr) {
		if (dateStr == null || dateStr.equals("")) {
			return null;
		}
		DateFormat sdf = new SimpleDateFormat(formatStr);
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * dateToString
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date) {
		if (null == date || "".equals(date)) {
			return null;
		}
		String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(date);
		return dateStr;
	}

	/**
	 * timestampToString
	 * 
	 * @param integer
	 * @return
	 */
	public static String timestampToString(Integer integer) {
		Date date = new Date(integer * 1000L);
		String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(date);
		return dateStr;
	}
	
	/**
	 * timestampToString
	 * @param integer
	 * @param format
	 * @return
	 */
	public static String timestampToString(Integer integer, String format) {
		Date date = new Date(integer * 1000L);
		String dateStr = new SimpleDateFormat(format)
				.format(date);
		return dateStr;
	}
	
	/**
	 * timestampToString
	 * @param integer
	 * @param format
	 * @return
	 */
	public static String timestampToString(long timestamp, String format) {
		Date date = new Date(timestamp);
		String dateStr = new SimpleDateFormat(format)
				.format(date);
		return dateStr;
	}

	/**
	 * stringToTimestamp
	 * 
	 * @param str
	 * @return
	 */
	public static Integer stringToTimestamp(String str) {
		if (str == null || str.equals("")) {
			return null;
		}
		Long l = null;
		String timestamp = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = null;
		try {
			d = sdf.parse(str);
			l = d.getTime();
			String str1 = String.valueOf(l);
			timestamp = str1.substring(0, 10);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return Integer.parseInt(timestamp);

	}

	/**
	 * getCurrentTimestamp
	 * 
	 * @return
	 */
	public static Integer getCurrentTimestamp() {
		Date d = new Date();
		Long l = d.getTime();
		String str1 = String.valueOf(l);
		String timestamp = str1.substring(0, 10);
		return Integer.parseInt(timestamp);

	}

	/**
	 * getCurrentDate
	 * 
	 * @param pattern
	 * @return
	 */
	public static String getCurrentDate(String pattern) {
		return new SimpleDateFormat(pattern).format(new Date());
	}

	/**
	 * getCurrentDate
	 * 
	 * @return
	 */
	public static String getCurrentDate() {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}
}
