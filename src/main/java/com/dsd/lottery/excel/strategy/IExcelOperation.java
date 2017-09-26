package com.dsd.lottery.excel.strategy;

import java.util.Map;

/**
 * excel操作类
 * @author daishengda
 *
 */
public interface IExcelOperation {

	Map<String, String[][]> parse();
	
	String create(Map<String, String[][]> dataMap);
}
