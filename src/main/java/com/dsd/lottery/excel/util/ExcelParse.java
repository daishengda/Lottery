package com.dsd.lottery.excel.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.dsd.lottery.excel.constant.ExcelConstant;
import com.dsd.lottery.util.log.LogUtil;

/**
 * excel解析
 * 
 * @author daishengda
 *
 */
public class ExcelParse {

	// Excel 2003
	private static final String EXCEL_XLS = "xls";

	// Excel 2007/2010
	private static final String EXCEL_XLSX = "xlsx";

	private Workbook workbok;

	private InputStream is;

	public ExcelParse(String file){
		try {
			is = new FileInputStream(file);
			if (file.endsWith(EXCEL_XLS)) {
				workbok = new HSSFWorkbook(is);
			} else if (file.endsWith(EXCEL_XLSX)) {
				workbok = new XSSFWorkbook(is);
			}
		} catch (IOException e) {
			LogUtil.error(ExcelConstant.LOG_EXCEL_PROJECTID, file+"excel create faild!", e);
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
	 * 获取sheet
	 * 
	 * @param sheetName
	 * @return
	 */
	public Sheet getSheet(String sheetName) {
		return workbok.getSheet(sheetName);
	}

	/**
	 * 获取sheet记录
	 * 
	 * @param sheetName
	 * @return
	 */
	public List<List<String>> getSheetData(String sheetName, int startRow,
			int startColumn) {
		Sheet sheet = getSheet(sheetName);
		return getSheetData(sheet, startRow, startColumn);
	}
	
	/**
	 * 获取sheet记录
	 * @param sheetindex
	 * @param startRow
	 * @param startColumn
	 * @return
	 */
	public List<List<String>> getSheetData(int sheetindex, int startRow,
			int startColumn) {
		Sheet sheet = getSheet(sheetindex);
		return getSheetData(sheet, startRow, startColumn);
	}
	
	/**
	 * 获取sheet记录
	 * 
	 * @param sheetName
	 * @return
	 */
	public List<List<String>> getSheetData(Sheet sheet, int startRow,
			int startColumn) {
		List<List<String>> sheetList = new ArrayList<List<String>>();
		List<String> rowList;
		Row row;
		for (int rowNum = startRow; rowNum <= sheet.getLastRowNum(); rowNum++) {
			row = sheet.getRow(rowNum);
			if (row != null) {
				rowList = new ArrayList<String>();
				for (int cellNum = startColumn; cellNum <= row.getLastCellNum(); cellNum++) {
					Cell cell = row.getCell(cellNum);
					if (cell != null) {
						Object cellValue = getCellValue(cell);
						if (cellValue == null) {
							break;
						}
						rowList.add(String.valueOf(cellValue));
					} else {
						break;
					}
				}
				if (!rowList.isEmpty()) {
					sheetList.add(rowList);
				}
			} else {
				break;
			}
		}
		return sheetList;
	}

	public Object getCellValue(Cell cell) {
		Object cellValue = null;
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_BOOLEAN:
			cellValue = cell.getBooleanCellValue();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			cellValue = cell.getNumericCellValue();
			break;
		case Cell.CELL_TYPE_STRING:
			cellValue = cell.getStringCellValue();
			break;
		default:
			break;
		}
		return cellValue;
	}

	public void close() {
		try {
			is.close();
		} catch (IOException e) {
			LogUtil.error(ExcelConstant.LOG_EXCEL_PROJECTID, "excel parse  closed faild!", e);
		}
	}
}
