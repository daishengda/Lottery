package com.dsd.lottery.util;

import java.text.MessageFormat;
import java.util.List;

public class ResourceUtil {

	/**
	 * 针对{0},{1}进行format
	 * @param format
	 * @param arguments
	 * @return
	 */
	public static String format(String format, Object...arguments)
	{
		return MessageFormat.format(format, arguments);
	}
	
	public static String append(int... strings)
	{
		StringBuilder sb = new StringBuilder();
		for(int str : strings)
		{
			sb.append(str);
		}
		return sb.toString();
	}
	
	/**
	 * 转换为个十百千(如0是个位)
	 * @param strings
	 * @return
	 */
	public static String convertDigit(int... strings)
	{
		StringBuilder sb = new StringBuilder();
		for(int num : strings)
		{
			sb.append(DigitUtil.convertUnit(num,""));
		}
		return sb.toString();
	}
	
	public static String append(List<Integer> strings)
	{
		StringBuilder sb = new StringBuilder();
		for(Integer str : strings)
		{
			sb.append(str);
		}
		return sb.toString();
	}
}
