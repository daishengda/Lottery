package com.dsd.lottery.util;

import org.apache.commons.lang3.math.NumberUtils;

public class DigitUtil {

	/**
	 * 位数转换描述
	 * @param num
	 * @return
	 */
	public static String convertUnit(Integer num) {
		return convertUnit(num, "位");
	}
	
	/**
	 * 位数转换描述
	 * @param num
	 * @return
	 */
	public static String convertUnit(Integer num, String desc) {
		String info;
		switch (num) {
		case 0:
			info = "万";
			break;
		case 1:
			info = "千";
			break;
		case 2:
			info = "百";
			break;
		case 3:
			info = "十";
			break;
		case 4:
			info = "个";
			break;
		default:
			info = "未知";
			break;
		}
		info += desc;
		return info;
	}
	
	/**
	 * 根据字符串的数字部分进行合并，转换为long，方便排序
	 * @param number
	 * @return
	 */
	public static long convertNumber(String str) {
		StringBuilder sortid = new StringBuilder();
		String temp;
		for(int i = 0;i< str.length();i++)
		{
			temp = str.substring(i, i+1);
			if(NumberUtils.isNumber(temp))
			{
				sortid.append(temp);
			}
		}
		return Long.parseLong(sortid.toString());
	}
}
