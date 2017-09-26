package com.dsd.lottery.excel.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.dsd.lottery.excel.constant.ExcelConstant;
import com.dsd.lottery.util.CloseUtil;
import com.dsd.lottery.util.FileUtil;
import com.dsd.lottery.util.log.LogUtil;

/**
 * 写入excel类
 * 
 * @author daishengda
 *
 */
public class ExcelCreate {

	// Excel 2003
	private static final String EXCEL_XLS = "xls";

	// Excel 2007/2010
	private static final String EXCEL_XLSX = "xlsx";

	/**
	 * 工作簿
	 */
	private Workbook workbok;

	private FileOutputStream out;

	private InputStream is;

	public ExcelCreate(String template, String toPath) {
		FileUtil.newFile(toPath);
		try {
			is = new FileInputStream(template);
			if (template.endsWith(EXCEL_XLS)) {
				workbok = new HSSFWorkbook(is);
			} else if (template.endsWith(EXCEL_XLSX)) {
				workbok = new XSSFWorkbook(is);
			}
			out = new FileOutputStream(toPath);
		} catch (IOException e) {
			LogUtil.error(ExcelConstant.LOG_EXCEL_PROJECTID, template
					+ "excel create faild!", e);
		}
	}
	
	/**
	 * 获取sheet数量
	 * 
	 * @return
	 */
	public int getNumberOfSheets() {
		return workbok.getNumberOfSheets();
	}
	
	/**
	 * 获取sheet
	 * 
	 * @param index
	 * @return
	 */
	public Sheet getSheet(int index) {
		return workbok.getSheetAt(index);
	}
	
	/**
	 * 从第几个sheet开始清空,从第几个结束
	 * @param startSheet
	 * @param endSheet
	 * @return
	 */
	public boolean cleanSheetData(int startSheet,int endSheet)
	{
		int sheetNum = getNumberOfSheets();
		int end = sheetNum-1;
		endSheet = endSheet >= end ? end : endSheet;
		Sheet sheet;
		for(int i = startSheet;i <= endSheet;i++)
		{
			sheet = getSheet(i);
			if(sheet != null)
			{
				Row row;
				for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
					
					row = sheet.getRow(rowNum);
					if(row!=null)
					{
						sheet.removeRow(row);
					}
				}
			}
		}
		return true;
	}

	/**
	 * 创建sheet并生产数据
	 * 
	 * @param sheetName
	 * @param data
	 */
	public void createSheet(String sheetName, String[][] data) {
		Sheet sheet = workbok.getSheet(sheetName);
		if(sheet == null)
		{
			workbok.createSheet(sheetName);
			sheet = workbok.getSheet(sheetName);
		}
		Row row;
		Cell cell;
		for (int i = 0; i < data.length; i++) {
			row = sheet.createRow(i);
			for (int j = 0; j < data[i].length; j++) {
				cell = row.createCell(j);
				cell.setCellValue(data[i][j]);
			}
		}
	}

	public void write() {
		try {
			workbok.write(out);
		} catch (IOException e) {
			LogUtil.error(ExcelConstant.LOG_EXCEL_PROJECTID,
					"excel write fail!", e);
		}
	}

	public void close() {
		CloseUtil.closeStream(is);
		CloseUtil.closeStream(out);
	}
}
