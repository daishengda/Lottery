package com.dsd.lottery.excel.constant;

import java.io.File;

/**
 * 常量
 * @author daishengda
 *
 */
public class ExcelConstant {
	
	/**
	 * 下载模板路径
	 */
	public static final String DOWNLOAD_TEMPLATE_PATH = ExcelConstant.class.getResource("/").getPath()+File.separator
			+"template"+File.separator+"彩票模板.xlsx";
	
	/**
	 * src/main/resources路径
	 */
	public static final String RESOURCES_PATH = "D:////lotteryFile"+File.separator;
	
	/**
	 * src/main/resources路径下的template
	 */
	public static final String TEMPLATE_PATH = RESOURCES_PATH+"template"+File.separator;
	
	/**
	 * src/main/resources路径下的generate(excel生成路径)
	 */
	public static final String GENERATE_PATH = RESOURCES_PATH+"generate"+File.separator;
	
	/**
	 * excel模板
	 */
	public static final String LOTTER_TEMPLATE_PATH = TEMPLATE_PATH+"彩票模板.xlsx";
	
	/**
	 * 待解析excel的sheetname
	 */
	public static final String SHEET_NAME = "号码";
	
	/**
	 * excel的日志projectid
	 */
	public static final String LOG_EXCEL_PROJECTID = "EXCEL_PARSE";
	
	/**
	 * 待解析excel的sheetindex
	 */
	public static final int SHEET_INDEX = 0;

	/**
	 * 5选3的位数矩阵
	 */
	public static final int[][] FIVE_SELECT_THREE = { { 0, 1, 2 }, { 0, 1, 3 },
			{ 0, 1, 4 }, { 0, 2, 3 }, { 0, 2, 4 }, { 0, 3, 4 }, { 1, 2, 3 },
			{ 1, 2, 4 }, { 1, 3, 4 }, { 2, 3, 4 } };

	/**
	 * 描述格式
	 */
	public static final String FORMAT_DESC = "从第{0}期开始出现{1}号码,一共用了{2}期";
	
}
