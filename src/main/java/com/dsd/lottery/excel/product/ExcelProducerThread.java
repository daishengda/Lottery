package com.dsd.lottery.excel.product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.StringUtils;

import com.dsd.lottery.excel.constant.ExcelConstant;
import com.dsd.lottery.excel.model.ProductModel;
import com.dsd.lottery.util.ResourceUtil;
import com.dsd.lottery.util.log.LogUtil;

/**
 * 生产者线程
 * 
 * @author daishengda
 *
 */
public class ExcelProducerThread implements Runnable {

	/**
	 * 每个sheet对应的彩票矩阵数据
	 */
	private String[][] dataArrays;

	private String sheetName;

	/**
	 * 仓库类
	 */
	private BlockingQueue<ProductModel> queue;

	private AtomicInteger atomic;

	private int diffStage;

	public ExcelProducerThread(String[][] dataArrays, String sheetName,
			BlockingQueue<ProductModel> queue, AtomicInteger atomic,
			int diffStage) {
		this.dataArrays = dataArrays;
		this.sheetName = sheetName;
		this.queue = queue;
		this.atomic = atomic;
		this.diffStage = diffStage;
	}

	@Override
	public void run() {
		for (int i = 0; i < dataArrays.length; i++) {
			dataArrays[i] = assembleResult(dataArrays[i], i);
		}
		try {
			queue.put(new ProductModel(dataArrays, sheetName));
		} catch (InterruptedException e) {
			LogUtil.error(ExcelConstant.LOG_EXCEL_PROJECTID,
					"queue push faild", e);
		} finally {
			atomic.decrementAndGet();
		}
	}

	private void handleDiff(List<Integer> arrays, String source) {
		Iterator<Integer> iterator = arrays.iterator();
		while (iterator.hasNext()) {
			if (source.indexOf(String.valueOf(iterator.next())) >= 0) {
				iterator.remove();
			}
		}
	}

	/**
	 * 获取备注信息
	 * 
	 * @param rowList
	 *            excel行数据
	 * @param rowIndex
	 *            excel行索引
	 */
	public String[] assembleResult(String[] rowArrays, int rowIndex) {
		ArrayList<Integer> arrays = getTenArrays();
		int length = rowArrays.length;
		ParseResult result = null;
		for (int i = 0; i < length; i++) {
			result = handleArrays(i, rowArrays, arrays);
			if (result != null) {
				break;
			}
		}
		// 扩容
		rowArrays = Arrays.copyOf(rowArrays, length + 1);
		if (result != null) {
			rowArrays[length++] = ResourceUtil.format(
					ExcelConstant.FORMAT_DESC, result.stage + 1,
					ResourceUtil.append(result.arrays), result.diffStage + 1);
		} else {
			rowArrays[length] = "无数据";
		}
		return rowArrays;
	}

	private ArrayList<Integer> getTenArrays() {
		ArrayList<Integer> arrays = new ArrayList<Integer>(10);
		int i = 0;
		while (i < 10) {
			arrays.add(i++);
		}
		return arrays;
	}

	@SuppressWarnings("unchecked")
	private ParseResult handleArrays(int start, String[] rowArrays,
			ArrayList<Integer> arrays) {
		List<Integer> tenList = (List<Integer>) arrays.clone();
		int length = rowArrays.length;
		for (; StringUtils.isBlank(rowArrays[length-1]);) {
			length--;
		}
		for (int i = start; i < length; i++) {

			if (i > start) {
				for (int j = 0; j < tenList.size(); j++) {
					tenList.set(j, (tenList.get(j) + diffStage) % 10);
				}
			}
			handleDiff(tenList, rowArrays[i]);
			int size = tenList.size();
			if (size < 3) {
				if (size < 2) {
					break;
				}
				if (size == 2 && i == length - 1) {
					return new ParseResult(i - start, start, tenList);
				}
			}
		}
		return null;
	}

	private class ParseResult {

		private int diffStage;

		private int stage;

		private List<Integer> arrays;

		public ParseResult(int diffStage, int stage, List<Integer> arrays) {
			this.diffStage = diffStage;
			this.stage = stage;
			this.arrays = arrays;
		}
	}
}
