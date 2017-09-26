package com.dsd.lottery.excel.model;

/**
 * 产品对象
 * @author daishengda
 *
 */
public class ProductModel{

	/**
	 * 每个sheet对应的彩票矩阵数据
	 */
	private String[][] excelData;
	
	/**
	 * sheet名称
	 */
	private String sheetName;

	public ProductModel() {
	}

	public ProductModel(String[][] excelData, String sheetName) {
		this.excelData = excelData;
		this.sheetName = sheetName;
	}

	public String[][] getExcelData() {
		return excelData;
	}

	public void setExcelData(String[][] excelData) {
		this.excelData = excelData;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

}
