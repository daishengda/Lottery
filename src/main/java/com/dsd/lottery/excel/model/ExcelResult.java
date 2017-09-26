package com.dsd.lottery.excel.model;

/**
 * 查询excel文件结果
 * 
 * @author daishengda
 *
 */
public class ExcelResult {

	/**
	 * 生成日期
	 */
	private String generateTime;

	/**
	 * 生成的文件名称
	 */
	private String fileName;

	public ExcelResult() {
	}

	public ExcelResult(String generateTime, String fileName) {
		this.generateTime = generateTime;
		this.fileName = fileName;
	}

	public String getGenerateTime() {
		return generateTime;
	}

	public void setGenerateTime(String generateTime) {
		this.generateTime = generateTime;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
