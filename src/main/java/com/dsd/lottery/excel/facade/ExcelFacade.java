package com.dsd.lottery.excel.facade;

import java.util.Map;

import com.dsd.lottery.excel.strategy.ExcelOperationImpl;
import com.dsd.lottery.excel.strategy.IExcelOperation;

/**
 * 
 * @author daishengda
 *
 */
public class ExcelFacade {

	private IExcelOperation operation;

	public ExcelFacade(String path, int diffStage) {
		operation = new ExcelOperationImpl(path, diffStage);
	}

	public String handle() {
		Map<String, String[][]> arraysMap = operation.parse();
		return operation.create(arraysMap);
	}
}
